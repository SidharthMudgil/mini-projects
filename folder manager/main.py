AUTHOR = 'SIDHARTH MUDGIL'

import os, shutil
from time import sleep

Type={
    'Audios' : ('.aif', '.cda', '.mid', '.midi', '.mp3', '.mpa', '.ogg', '.wav', '.wma', '.wpl'),
    'Compressed' : ('.7z', '.arj', '.deb', '.pkg', '.rar', '.rpm', '.tar.gz', '.z', '.zip'),
    'Disc_Media' : ('.bin', '.dmg', '.iso', '.toast', '.vcd'),
    'DataBases' : ('.csv', '.dat', '.db', '.dbf', '.log', '.mdb', '.sav', '.sql', '.tar', '.xml'),
    'Mails' : ('.email', '.eml', '.emlx', '.msg', '.oft', '.ost', '.pst', '.vcf'),
    'Executables' : ('.apk', '.bat', '.bin', '.cgi', '.pl', '.com', '.exe', '.gadget', '.jar', '.msi', '.py', '.wsf'),
    'Fonts' : ('.fnt', '.fon', '.otf', '.ttf'),
    'Images' : ('.bmp', '.gif', '.ico', '.jpeg', '.jpg', '.png', '.ps', '.psd', '.svg', '.tif', '.tiff'),
    'Internet' : ('.asp', '.aspx', '.cer', '.cfm', '.cgi', '.pl', '.css', '.htm', '.html', '.js', '.jsp', '.part', '.php', '.py', '.rss', '.xhtml'),
    'Presentations' : ('.key', '.odp', '.pps', '.ppt', '.pptx'),
    'Programmings' : ('.c', '.class', '.cpp', '.cs', '.h', '.java', '.pl', '.sh', '.swift', '.vb'),
    'Spreadsheets' : ('.ods', '.xls', '.xlsm', '.xlsx', '.accdb'),
    'Others' : ('.bak', '.cab', '.cfg', '.cpl', '.cur', '.dll', '.dmp', '.drv', '.icns', '.ico', '.ini', '.lnk', '.msi', '.sys', '.tmp',''),
    'Videos' : ('.3g2', '.3gp', '.avi', '.flv', '.h264', '.m4v', '.mkv', '.mov', '.mp4', '.mpg', '.mpeg', '.rm', '.swf', '.vob', '.wmv'),
    'Word_Processors' : ('.doc', '.docx', '.odt', '.pdf', '.rtf', '.tex', '.txt', '.wpd'),
}



def Caller():
    os.system('cls')
    print('Enter The path to Manage ( "PATH" ): ')
    Input=input('>>>')
    if Input[0]=='"':
        x,Path,y=Input.split('"')
    else:
        Path=Input
        
    try:
        os.chdir(Path)
        for i in range(4):
            print(f'The Path is Processing ( Wait {3-i} )',end='\r',flush='True')
            sleep(1)

    except FileNotFoundError:
        print('Enter a Correct Path')
        Main()
    os.system('cls')


def Mover():
    for i in os.listdir():
        Name1,Ext1=os.path.splitext(i)
        for Name,Extension in Type.items():
            for Ext in Extension:
                if Ext==Ext1:
                    if os.path.exists(Name):
                        pass
                    else:
                        os.mkdir(Name)
                    try:
                        shutil.move(i,Name)
                    except PermissionError:
                        pass
    try:
        for i in os.listdir():
            os.rmdir(i)
    except:
        pass

def Main():
    Caller()
    Mover()

if __name__ == "__main__":
    Main()
