import os
import shutil

COMMANDS = ('open', 'mv', 'cp', 'new', 'del', 'up', 'exit')

def menu():
    print("open : open 'path'")
    print("new  : new 'path'")
    print("del  : del 'path'")
    print("mv   : mv 'from' 'to'")
    print("cp   : cp 'from' 'to'")
    print("up   : up")
    print("exit : exit")
    print('*' * 50)
    curr_dir = os.getcwd()
    for files in os.listdir(curr_dir):
        print(files)
    print('*' * 50)


def clean_input():
    _input = input('>').strip().lower()

    while "  " in _input:
        _input = _input.replace("  ", ' ')

    query = _input.split(' ')

    if query[0] in COMMANDS:
        return query

    print(f"command {query[0]} not found")


def file_explorer(pathway=[]):
    os.system('cls')

    if len(pathway) == 1:
        location = pathway[0]
        os.chdir(location)

    menu()
    query = clean_input()

    if query[0] == 'exit':
        return
    elif query[0] == 'up':
        if len(pathway) == 0:
            return
        else:
            pathway.pop()
            file_explorer(pathway)

    if len(query) == 2:
        command = query[0]
        path = query[1].strip().strip('"')

        if command == 'open':
            if os.path.exists(path):
                if os.path.isfile(path):
                    with open(path) as file:
                        for line in file:
                            print(line)
                elif os.path.isdir(path):
                    pathway.append(path)
                    file_explorer(pathway)
            else:
                print("Path does not exist")

        elif command == 'new':
            if not os.path.exists(path):
                if '.' in os.path.basename(path):
                    with open(path, 'w') as file:
                        pass
                else:
                    os.mkdir(path)
            else:
                print("Path already exists")

        elif command == 'del':
            if os.path.exists(path):
                if '.' in os.path.basename(path):
                    os.remove(path)
                else:
                    shutil.rmtree(path)
            else:
                print("Path does not exist")

    elif len(query) == 3:
        command = query[0]
        path1 = query[1].strip().strip('"')
        path2 = query[2].strip().strip('"')

        if command == 'cp':
            if os.path.exists(path1) and os.path.exists(path2):
                if '.' in os.path.basename(path1):
                    shutil.copy(path1, path2)
                else:
                    shutil.copytree(path1, path2)
            else:
                print("Either or both paths do not exist")

        elif command == 'mv':
            if os.path.exists(path1) and os.path.exists(path2):
                shutil.move(path1, path2)
            else:
                print("Either or both paths do not exist")

    file_explorer(pathway)


file_explorer()
