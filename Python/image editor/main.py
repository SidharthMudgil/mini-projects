import os
import shutil
import time
from ImageUtils import ImageUtil

def DateTime():
    print(time.strftime("%D %m-%Y"), end="")
    print(time.strftime("%I:%M %p"))


def FileExplorer(Pathway=['F:\\']):
    os.system('cls')
    DateTime()
    print("F I L E   E X P L O R E R")
    print("OPEN    : OPEN>PATH")
    print("MOVE    : MOVE>PATH1>PATH2")
    print("CREATEF : CREATEF>PATH")
    print("COPY    : COPY>PATH1>PATH2")
    print("DELETEF : DELETEF>PATH")
    print("CREATEf : CREATEf>PATH")
    print("0/X     : BACK/EXIT")
    print("DELETEf : DELETEf>PATH")
    print(">>>")
    count = 0
    if len(Pathway) == 1:
        Location = Pathway[len(Pathway)-1]
        os.chdir(Location)
    Current = os.getcwd()
    for i in range(1):
        print('"' + ' '*164 + '"', end="")

    for fILEfOLDER in os.listdir(Current):
        print(f'" '+fILEfOLDER)
        i += 1

    Type = ''
    Input = input("\nINPUT COMMAND >>> ")
    for i in Input:
        if i == '>':
            count += 1

    if count == 0:
        if Input.lower() == 'x':
            Menu()
        elif Input == '0':
            if len(Pathway) == 1:
                Menu()
                FileExplorer()
            elif len(Pathway) > 1:
                Pathway.pop()
                Location = ''
                for i in Pathway:
                    Location = i+'/'
                os.chdir(Location)
                FileExplorer(Pathway)
        else:
            print()
            FileExplorer()
    elif count == 1 or count == 2:
        if count == 1:
            a = Input.split('>')
            Command = a[0]
            x = a[1]
            a, Path, b = x.split('"')
            for i in Path:
                if i == '.':
                    Type = 'file'
                else:
                    Type = 'folder'

        elif count == 2:
            a = Input.split('>')
            Command = a[0]
            x, y = a[1], a[2]
            a, Path1, b = x.split('"')
            a, Path2, b = y.split('"')
            for i in Path1:
                if i == '.':
                    Type = 'file'
                else:
                    Type = 'folder'
            print(Type)
        if Command.lower() == 'open':
            if os.path.exists(Path):
                f = open(Path)
                print("."*136+"\n")
                for i in f.readlines():
                    print(i)
                print("."*136)
                os.system('Pause')
            else:
                print("File not found")
            FileExplorer()
            if Type == 'folder':
                if os.path.exists(Path):
                    Pathway.append(Path)
                    Location = Pathway[len(Pathway)-1]
                    os.chdir(Location)
                    FileExplorer(Pathway)
                else:
                    print("File not found")
                FileExplorer()

        elif Command.lower() == 'copy':
            if os.path.exists(Path2):
                print("File found")
            elif os.path.exists(Path1):
                shutil.copy(Path1, Path2)
            else:
                print("File not found")
            FileExplorer()
            if Type == 'folder':
                if os.path.exists(Path2):
                    print("File found")
                elif os.path.exists(Path1):
                    shutil.copytree(Path1, Path2)
                else:
                    print("File not found")
                FileExplorer()

        elif Command.lower() == 'move':
            if os.path.exists(Path1) and os.path.exists(Path2):
                shutil.move(Path1, Path2)
            else:
                print("File not found")
            FileExplorer()

        elif Command == 'CREATEF':
            if os.path.exists(Path):
                print("File found")
            else:
                os.mkdir(Path)
            FileExplorer()

        elif Command == 'CREATEf':
            if os.path.exists(Path):
                print("File found")
            else:
                with open(Path, 'a') as f:
                    print('', end="")
            FileExplorer()

        elif Command == 'DELETEF':
            if os.path.exists(Path):
                shutil.rmtree(Path)
            else:
                print("File not found")
            FileExplorer()

        elif Command == 'DELETEf':
            if os.path.exists(Path):
                os.remove(Path)
            else:
                print("File not found")
            FileExplorer()

        else:
            print()
            FileExplorer()

    else:
        print()
        FileExplorer()


def ImageEditor():
    os.system('cls')
    DateTime()
    print(" .___________________________.")
    print(" |                           |")
    print(" | I M A G E -- E D I T O R  |")
    print(" |                           |")
    print(" |   1 --> Open/SHOW         |")
    print(" |                           |")
    print(" |   2 --> Resize/Crop       |")
    print(" |                           |")
    print(" |   3 --> ADJUST Brightness |")
    print(" |                           |")
    print(" |   4 --> ADJUST Sharpness  |")
    print(" |                           |")
    print(" |   5 --> ADJUST Color      |")
    print(" |                           |")
    print(" |   6 --> ADJUST Contrast   |")
    print(" |                           |")
    print(" |   7 --> ADJUST ROTATION   |")
    print(" |                           |")
    print(" |   8 --> Blur              |")
    print(" |                           |")
    print(" |   0 --> EXIT              |")
    print(" |___________________________|")
    a = input('>>>')

    if a == '0':
        Menu()
    else:
        path = input("COPY THE IMAGE PATH AND PASTE HERE >>>")
        try:
            x, Path, y = path.split('"')
        except ValueError:
            Path = path
        except FileNotFoundError:
            print("File Not Found")

        image = ImageUtil(Path)
        if a == '1':
            image.open()

        elif a == '2':
            image.crop()

        elif a == '3':
            image.change_brightness()

        elif a == '4':
            image.change_sharpness()

        elif a == '5':
            image.chage_color()

        elif a == '6':
            image.change_contrast()

        elif a == '7':
            image.rotate()

        elif a == '8':
            image.blur()

        ImageEditor()


def Menu():
    os.system('cls')
    DateTime()
    print(" .__________________________.")
    print(" |                          |")
    print(" |        M  E  N  U        |")
    print(" |                          |")
    print(" |   1 -->  FILE EXPLORER   |")
    print(" |                          |")
    print(" |    2 ->  IMAGE EDITOR    |")
    print(" |                          |")
    print(" |       0 --> EXIT         |")
    print(" |__________________________|")
    c = input('>>>')
    if c == '1':
        FileExplorer()
    elif c == '2':
        ImageEditor()
    elif c == '0':
        exit()


if __name__ == "__main__":
    try:
        Menu()
    except KeyboardInterrupt:
        Menu()
