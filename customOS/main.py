import os,shutil
import time
import datetime
from datetime import date
import threading
from PIL import Image,ImageEnhance,ImageFilter


def Loading(speed=0.15):
    space=" "
    load=['|','/','-','\\']
    print()
    for i in range(1):
        for j in load:
            print(f"\r{space*52}{j*70}",flush=True,end="")
            time.sleep(speed)
    print(f"\r{space*166}",end="",flush=True)



def Bar(a=0.00001,b=0):
    os.system('cls')
    space=""
    line1=","*166
    print('\n'*23)
    print(line1,end="",flush=True)
    for i in range(83):
        print("[]",end="",flush=True)
        time.sleep(a)
        if i==5 or i==13:
            time.sleep(b+i/1000)
    os.system('cls')


def DateTime():
    print('"'*166,end="")
    print("\" DATE : ",end="",flush=True)
    print(time.strftime("%D %m-%Y"),end="")
    print(" "*118+"TIME : ",end="",flush=True)
    print(time.strftime("%I:%M %p")+' '*7+'"',end="")
    print('"' + ' '*164 + '"' +'"'*166,end="")
   

def Check():
    check=['Audio','RAM','ROM','FILES','FOLDER',"DISK \\0",'GRAPHICS','CACHE','TEMP','PATH','SERVICES','SYSTEM','DESKTOP']
    for j in  range (1,13):
        print()
        for i in range(1,101):
            time.sleep(0.0001)
            print(f"\rChecking C:\\{check[j]} : {i}%",end='',flush=True)
            if i==70:
                print(f"\t\t\t\t\t\tOK ({j}/12)",end="")

    os.system('cls')
   


def Welcome():
    os.system('cls')
    print("\n"*22,end="")
    for i in range(2):
        print("\r\t\t\t\t\t\t\t\t\t  B O O T I N G  U P  W I N D O W",end="")
        time.sleep(0.3)
        print("\r\t\t\t\t\t\t\t\t\t                                 ",end="",flush=True)
        time.sleep(0.5)
    os.system('cls')
    Check()
    os.system('cls')
    print("\n"*22,end="")
    text="W E L C O M E"
    text2="BOOTING COMPLETED"
    x=""
    for i in text2:
        x+=" " + i
        print("\r\t\t\t\t\t\t\t\t      "+f"{x} ",end="")
        time.sleep(0.1)
    print("\r\t\t\t\t\t\t\t\t\t              ",end="")
    os.system('cls')
    print("\n"*22,end="")
    for i in text:
        print("\r\t\t\t\t\t\t\t\t\t\t\t"+i,end="")
        time.sleep(0.1)
    print("\r\t\t\t\t\t\t\t\t\t\t     "+text,end="")
    time.sleep(1)
    print("\r\t\t\t\t\t\t\t\t\t\t        ",end="")
    os.system('cls')
    


def Invalid():
    os.system('cls')
    invalid="INVALID !!"
    valid="ENTER A VALID MENU NUMBER..."
    s=0
    x=0.001
    print("\n"*20)
    print("\r\t\t\t\t\t\t\t\t\t\t ",end="")
    for i in invalid:
        print(f"{i} ",end="",flush="True")
        time.sleep(0.03)
        
    print("\r\n\n\t\t\t\t\t\t\t\t",end="")
    for i in valid:
        print(f"{i} ",end="",flush=True)
        time.sleep(s)
        s=s+x
        if i.lower()=='.':
            time.sleep(0.4)
    os.system('cls')


def Task():
    os.system('cls')
    task='TASK SUCCESSFUL...'
    s=0
    x=0.001
    print("\n"*22)
    print("\r\n\n\t\t\t\t\t\t\t\t       ",end="")
    for i in task:
        print(f"{i} ",end="",flush=True)
        time.sleep(s)
        s=s+x
        if i.lower()=='.':
            time.sleep(0.4)
    os.system('cls')

def Xcode():
    os.system('cls')
    print("\n"*17)
    Xcode=["1111111111111    111111111111    111111111111     1111111111111    111111111111 ",
    "1111111111111    111       111   111       111   111111111111111   111       111",
    "111              111       111   111       111   111         111   111       111",
    "111              111       111   111       111   111         111   111       111",
    "11111111111      111       111   111       111   111         111   111       111",
    "11111111111      111111111111    111111111111    111         111   111111111111 ",
    "111              111   111       111   111       111         111   111    111   ",
    "111              111    111      111     111     111         111   111     111  ",
    "11111111111111   111      111    111       111   111111111111111   111      111 ",
    "11111111111111   111       111   111        111   1111111111111    111       111"]
    for i in Xcode:
        print(' '*49,end="")
        for j in i:
            print(f"{j}",end="",flush=True)
            time.sleep(0.0001)
        print(""*136)
    print('\n'*5)
    os.system('cls')

def Error():
    os.system('cls')
    Error="E R R O R"
    print("\n"*23)
    for i in range(3):
        print(f"\r\t\t\t\t\t\t\t\t\t\t   {Error}",end="",flush=True)
        time.sleep(0.5)
        print(f"\r\t\t\t\t\t\t\t\t\t\t            ",end="",flush=True)
        time.sleep(0.5)

def FileNotFound():
    os.system('cls')
    Error="F I L E / F O L D E R  C A N'T  B E  F O U N D"
    print("\n"*23)
    for i in range(3):
        print(f"\r\t\t\t\t\t\t\t\t  {Error}",end="",flush=True)
        time.sleep(0.5)
        print(f"\r\t\t\t\t\t\t\t\t                                                ",end="",flush=True)
        time.sleep(0.5)
    
def FileFound():
    os.system('cls')
    Error="C A N'T  B E  C R E A T E D"
    print("\n"*23)
    for i in range(3):
        print(f"\r\t\t\t\t\t\t\t\t\t   {Error}",end="",flush=True)
        time.sleep(0.5)
        print(f"\r\t\t\t\t\t\t\t\t\t                              ",end="",flush=True)
        time.sleep(0.5)

def FileExplorer(Pathway=['G:\\']):
    Loading()
    os.system('cls')
    DateTime()
    print('"\t\t\t\t\t\t\t\t "' + " "*40 + '"' + ' '*58 + '"')
    print('"\tOPEN    : OPEN>PATH\t\t\t\t\t "' + "     - " + "F I L E   E X P L O R E R" + " -      " + '"\t   MOVE    : MOVE>PATH1>PATH2' + ' '*24 + '"')
    print('"\tCREATEF : CREATEF>PATH\t\t\t\t\t "' + " "*40 + '"\t   COPY    : COPY>PATH1>PATH2' + ' '*24 + '"')
    print('"\tDELETEF : DELETEF>PATH\t\t\t\t\t "' + '"'*40 + '"' + '\t   CREATEf : CREATEf>PATH' + ' '*28 + '"')
    print('"\t0/X     : BACK/EXIT' + " "*65 + '\t\t\t   DELETEf : DELETEf>PATH' + ' '*28 + '"' + '"' + ' '*164 + '"' + '"'*166,end="")
    count=0
    if len(Pathway)==1:
        Location=Pathway[len(Pathway)-1]
        os.chdir(Location)  
    Current=os.getcwd()
    for i in range(1):
        print('"' + ' '*164 + '"',end="")

    for fILEfOLDER in os.listdir(Current):
        print(f'" '+fILEfOLDER)
        i+=1
        time.sleep(0.001)

    for i in range(1):
        print('"' + ' '*164 + '"',end="")
    print('"'*166,end="")
    Type=''
    Input=input("\nINPUT COMMAND >>> ")
    for i in Input:
        if i=='>':
            count+=1

    if count==0:
        if Input.lower()=='x':
            Loading()
            Bar()
            Menu()
        elif Input=='0':
            if len(Pathway)==1:
                Loading()
                Bar()
                Menu()
                FileExplorer()
            elif len(Pathway)>1:
                Pathway.pop()
                Location=''
                for i in Pathway:
                    Location=i+'/'
                os.chdir(Location)
                FileExplorer(Pathway)
        else:
            Invalid()
            FileExplorer()
    elif count==1 or count==2:        
        if count==1:
            a=Input.split('>')
            Command=a[0]
            x=a[1]
            a,Path,b=x.split('"')
            for i in Path:
                if i=='.':
                    Type='file'
                else:
                    Type='folder'

        elif count==2:
            a=Input.split('>')
            Command=a[0]
            x,y=a[1],a[2]
            a,Path1,b=x.split('"')
            a,Path2,b=y.split('"')
            for i in Path1:
                if i=='.':
                    Type='file'
                else:
                    Type='folder'
            print(Type)
        if Command.lower()=='open':
            if os.path.exists(Path):
                f=open(Path)
                print("."*136+"\n")
                for i in f.readlines():
                    print(i)
                print("."*136)
                os.system('Pause')
            else:
                FileNotFound()
            FileExplorer()
            if Type=='folder':
                if os.path.exists(Path):
                    Pathway.append(Path)
                    Location=Pathway[len(Pathway)-1]
                    os.chdir(Location)
                    FileExplorer(Pathway)
                else:
                    FileNotFound()
                FileExplorer()

        elif Command.lower()=='copy':
            if os.path.exists(Path2):
                FileFound()
            elif os.path.exists(Path1):
                shutil.copy(Path1,Path2)
            else:
                FileNotFound()
            FileExplorer()
            if Type=='folder':
                if os.path.exists(Path2):
                    FileFound()
                elif os.path.exists(Path1):
                    shutil.copytree(Path1,Path2)
                else:
                    FileNotFound()
                FileExplorer()


        elif Command.lower()=='move':
            if os.path.exists(Path1) and os.path.exists(Path2):
                shutil.move(Path1,Path2)
            else:
                FileNotFound()
            FileExplorer()

        elif Command=='CREATEF':
            if os.path.exists(Path):
                FileFound()
            else:
                os.mkdir(Path)
            FileExplorer()

        elif Command=='CREATEf':
            if os.path.exists(Path):
                FileFound()
            else:
                with open(Path,'a') as f:
                    print('',end="")
            FileExplorer()

        elif Command=='DELETEF':
            if os.path.exists(Path):
                shutil.rmtree(Path)
            else:
                FileNotFound()
            FileExplorer()

        elif Command=='DELETEf':
            if os.path.exists(Path):
                os.remove(Path)
            else:
                FileNotFound()
            FileExplorer()

        else:
            Invalid()
            FileExplorer()

    else:
        Invalid()
        FileExplorer()
  




def ImageEditor():
    Loading()
    os.system('cls')
    DateTime()
    print('"\t\t\t\t\t\t\t       "' + " "*40 + '"' + ' '*60 + '"')
    print('"\t\t\t\t\t\t\t       " ' + "     - " + "I M A G E _ E D I T O R" + " -      " + ' "' + ' '*60 + '"')
    print('"\t\t\t\t\t\t\t       "' + " "*40 + '"' + ' '*60 + '"')
    print('"\t\t\t\t\t\t\t       "' + '"'*40 + '"' + ' '*60 + '"')
    for i in range(7):
        print('"' + ' '*164 + '"')
    print("\"\t\t\t\t\t\t\t\t       .___________________________.\t"  + ' '*61 + '"')
    time.sleep(0.2)
    print("\"\t\t\t\t\t\t\t\t       |                           |\t"  + ' '*61 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       | I M A G E -- E D I T O R  |\t"  + ' '*61 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |                           |\t"  + ' '*61 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |   1 --> Open/SHOW         |\t"  + ' '*61 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |                           |\t"  + ' '*61 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |   2 --> Resize/Crop       |\t"  + ' '*61 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |                           |\t"  + ' '*61 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |   3 --> ADJUST Brightness |\t"  + ' '*61 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |                           |\t"  + ' '*61 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |   4 --> ADJUST Sharpness  |\t"  + ' '*61 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |                           |\t"  + ' '*61 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |   5 --> ADJUST Color      |\t"  + ' '*61 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |                           |\t"  + ' '*61 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |   6 --> ADJUST Contrast   |\t"  + ' '*61 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |                           |\t"  + ' '*61 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |   7 --> ADJUST ROTATION   |\t"  + ' '*61 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |                           |\t"  + ' '*61 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |   8 --> Blur              |\t"  + ' '*61 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |                           |\t"  + ' '*61 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |   0 --> EXIT              |\t"  + ' '*61 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |___________________________|\t"  + ' '*61 + '"')
    time.sleep(0.01)
    for i in range(9):
        print('"' + ' '*164 + '"',end="")
    print('"'*166,end="")
    a=input('>>>')
    if a=='0':
        try:
            Loading()
            Menu()
        except:
            Error()

    elif a=='1':
        Loading()
        path=input("COPY THE IMAGE PATH AND PASTE HERE >>>")
        try:
            x,Path,y=path.split('"')
            image=Image.open(Path)
            image.show()
        except:
            Error()
        finally:
            ImageEditor()

    elif a=='2':
        Loading()
        path=input("COPY THE IMAGE PATH AND PASTE HERE >>>")
        try:
            x,Path,y=path.split('"')
            image=Image.open(Path)
            Val1,Val2=input("ENTER THE VALUES (0,0) : ").split(',')
            Val=(int(Val1),int(Val2))
            image.thumbnail(Val)
            image.save(Path)
            Task()
        except:
            Error()
        finally:
            ImageEditor()

    elif a=='3':
        Loading()
        path=input("COPY THE IMAGE PATH AND PASTE HERE >>>")
        try:
            x,Path,y=path.split('"')
            image=Image.open(Path)
            Val=int(input("ENTER THE VALUE : "))
            mod=ImageEnhance.Brightness(image)
            mod.enhance(Val).save(Path)
            Task()
        except ValueError:
            Error()
        finally:
            ImageEditor()


    elif a=='4':
        Loading()
        path=input("COPY THE IMAGE PATH AND PASTE HERE >>>")
        try:
            x,Path,y=path.split('"')
            image=Image.open(Path)
            Val=int(input("ENTER THE VALUE : "))
            mod=ImageEnhance.Sharpness(image)
            mod.enhance(Val).save(Path)
            Task()
        except ValueError:
            Error()
        finally:
            ImageEditor()

        
    elif a=='5':
        Loading()
        path=input("COPY THE IMAGE PATH AND PASTE HERE >>>")
        try:
            x,Path,y=path.split('"')
            image=Image.open(Path)
            Val=int(input("ENTER THE VALUE : "))
            mod=ImageEnhance.Color(image)
            mod.enhance(Val).save(Path)
            Task()
        except ValueError:
            Error()
        finally:
            ImageEditor()

    elif a=='6':
        Loading()
        path=input("COPY THE IMAGE PATH AND PASTE HERE >>>")
        try:
            x,Path,y=path.split('"')
            image=Image.open(Path)
            Val=int(input("ENTER THE VALUE : "))
            mod=ImageEnhance.Contrast(image)
            mod.enhance(Val).save(Path)
            Task()
        except ValueError:
            Error()
        finally:
            ImageEditor()

    elif a=='7':
        Loading()
        path=input("COPY THE IMAGE PATH AND PASTE HERE >>>")
        try:
            x,Path,y=path.split('"')
            image=Image.open(Path)
            Val=int(input("ENTER THE VALUE : "))
            image.rotate(Val).save(Path)
            Task()
        except ValueError:
            Error()
        finally:
            ImageEditor()

    elif a=='8':
        Loading()
        path=input("COPY THE IMAGE PATH AND PASTE HERE >>>")
        try:
            x,Path,y=path.split('"')
            image=Image.open(Path)
            Val=int(input("ENTER THE VALUE : "))
            image.filter(ImageFilter.GaussianBlur(radius=Val)).save(Path)
            Task()
        except ValueError:
            Error()
        finally:
            ImageEditor()

    else:
        try:
            Invalid()
        except  ValueError:
            Error()
        finally:
            ImageEditor()




def Menu():
    os.system('cls')
    DateTime()
    print('"\t\t\t\t\t\t\t       "' + " "*40 + '"' + ' '*60 + '"')
    print('"\t\t\t\t\t\t\t       " ' + "- " + "S I D H A R T H ' S  __  M E N U :" + " -" + ' "' + ' '*60 + '"')
    print('"\t\t\t\t\t\t\t       "' + " "*40 + '"' + ' '*60 + '"')
    print('"\t\t\t\t\t\t\t       "' + '"'*40 + '"' + ' '*60 + '"')
    for i in range(12):
        print('"' + ' '*164 + '"')
    print("\"\t\t\t\t\t\t\t\t       .__________________________.    "  + ' '*62 + '"')
    time.sleep(0.07)
    print("\"\t\t\t\t\t\t\t\t       |                          |    "  + ' '*62 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |        M  E  N  U        |    "  + ' '*62 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |                          |    "  + ' '*62 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |   1 -->  FILE EXPLORER   |    "  + ' '*62 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |                          |    "  + ' '*62 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |    2 ->  IMAGE EDITOR    |    "  + ' '*62 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |                          |    "  + ' '*62 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |       0 --> HOME         |    "  + ' '*62 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t       |__________________________|    "  + ' '*62 + '"')
    time.sleep(0.01)
    for i in range(16):
        print('"' + ' '*164 + '"')
    print('"'*166,end="")
    c=input('>>>')
    if c=='1':
        FileExplorer()
    elif c=='2':
        ImageEditor()
    elif c=='0':
        Bar()
        Desktop()    
    else:
        Loading(0.15)
        Invalid()
        Menu()



def Desktop():
    os.system('cls')
    DateTime()
    print('"\t\t\t\t\t\t\t       "' + " "*40 + '"' + ' '*60 + '"')
    print('"\t\t\t\t\t\t\t       " ' + "- " + "S I D H A R T H ' S  D E S K T O P" + " -" + ' "' + ' '*60 + '"')
    print('"\t\t\t\t\t\t\t       "' + " "*40 + '"' + ' '*60 + '"')
    print('"\t\t\t\t\t\t\t       "' + '"'*40 + '"' + ' '*60 + '"')
    for i in range(10):
        print('"' + ' '*164 + '"')
    print("\"\t\t\t\t\t\t\t\t      /'''''''''''''''''''''''''''\\"  + ' '*66 + '"')
    time.sleep(0.07)
    print("\"\t\t\t\t\t\t\t\t      |                           |"  + ' '*66 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t      |       D E S K T O P       |"  + ' '*66 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t      |                           |"  + ' '*66 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t      | ENTER* ->  GO TO THE MENU |"  + ' '*66 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t      |                           |"  + ' '*66 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t      |   Q -->  REFRESH DESKTOP  |"  + ' '*66 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t      |                           |"  + ' '*66 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t      |   E --->  RESTART WINDOW  |"  + ' '*66 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t      |                           |"  + ' '*66 + '"')
    time.sleep(0.01)
    print("\"\t\t\t\t\t\t\t\t      |      ~ -->  SHUTDOWN      |"  + ' '*66 + '"')
    time.sleep(0.01)
    print('\"\t\t\t\t\t\t\t\t      \\,,,,,,,,,,,,,,,,,,,,,,,,,,,/' + ' '*66 + '"')
    time.sleep(0.01)
    for i in range(16):
        print('"' + ' '*164 + '"')
    print('"'*166,end="")
    C=input('>>>')
    while True:
        if C=='':
            Bar(0.003,0.15)
            Menu()
        elif C.lower()=='q':
            Desktop()
        elif C.lower()=='e':
            System()
        elif C.lower()=="~":    
            Bar()
            exit()
        else:
            Invalid()
            Bar()
            Desktop()


def System():
    os.system('cls')
    Welcome()
    Desktop()




try:
    System()
except KeyboardInterrupt :
    Xcode()
    System()





