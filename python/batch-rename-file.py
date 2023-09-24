import os
root = os.getcwd()
for file in os.listdir('.'):
   if not os.path.isfile(file):
       continue

   head, tail = os.path.splitext(file)
   if not tail:
       src = os.path.join(root, file)
       dst = os.path.join(root, file + '.png')

       if not os.path.exists(dst): # check if the file doesn't exist
           os.rename(src, dst)