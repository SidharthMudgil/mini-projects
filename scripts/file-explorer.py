import os
import shutil

def file_explorer(Pathway=['F:\\']):
    os.system('cls')
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

    i = 0

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
            menu()
        elif Input == '0':
            if len(Pathway) == 1:
                menu()
                file_explorer()
            elif len(Pathway) > 1:
                Pathway.pop()
                Location = ''
                for i in Pathway:
                    Location = i+'/'
                os.chdir(Location)
                file_explorer(Pathway)
        else:
            print()
            file_explorer()
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
            file_explorer()
            if Type == 'folder':
                if os.path.exists(Path):
                    Pathway.append(Path)
                    Location = Pathway[len(Pathway)-1]
                    os.chdir(Location)
                    file_explorer(Pathway)
                else:
                    print("File not found")
                file_explorer()

        elif Command.lower() == 'copy':
            if os.path.exists(Path2):
                print("File found")
            elif os.path.exists(Path1):
                shutil.copy(Path1, Path2)
            else:
                print("File not found")
            file_explorer()
            if Type == 'folder':
                if os.path.exists(Path2):
                    print("File found")
                elif os.path.exists(Path1):
                    shutil.copytree(Path1, Path2)
                else:
                    print("File not found")
                file_explorer()

        elif Command.lower() == 'move':
            if os.path.exists(Path1) and os.path.exists(Path2):
                shutil.move(Path1, Path2)
            else:
                print("File not found")
            file_explorer()

        elif Command == 'CREATEF':
            if os.path.exists(Path):
                print("File found")
            else:
                os.mkdir(Path)
            file_explorer()

        elif Command == 'CREATEf':
            if os.path.exists(Path):
                print("File found")
            else:
                with open(Path, 'a') as f:
                    print('', end="")
            file_explorer()

        elif Command == 'DELETEF':
            if os.path.exists(Path):
                shutil.rmtree(Path)
            else:
                print("File not found")
            file_explorer()

        elif Command == 'DELETEf':
            if os.path.exists(Path):
                os.remove(Path)
            else:
                print("File not found")
            file_explorer()

        else:
            print()
            file_explorer()

    else:
        print()
        file_explorer()


file_explorer()