AUTHOR = 'SIDHARTH MUDGIL'

import os, shutil

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

def main():
    # while True:
    path = input('directory path [ex -> C:/folder1]: ').strip()
        # try:
    os.chdir(path)
        # break
        # except FileNotFoundError:
            # print('Enter a Correct Path')


def manager():
    for file in os.listdir():
        name, ext = os.path.splitext(file)
        
        for dir_name in Type.keys():
            if ext.lower() in Type[dir_name]:
                if not os.path.exists(dir_name):
                    os.mkdir(dir_name)

                try:
                    shutil.move(file, dir_name)
                except PermissionError:
                    pass


    for directory in os.listdir():
        try:
            os.rmdir(directory)
        except OSError:
            pass

main()
manager()