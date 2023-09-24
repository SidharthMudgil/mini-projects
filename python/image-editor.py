import os
from PIL import Image
from PIL import ImageEnhance
from PIL import ImageFilter

class ImageUtil:
    def __init__(self, path) -> None:
        self.path = path
        self.image = Image.open(path)

    def open(self):
        self.image.show()

    def crop(self):
        try:
            x, y = input("(X,Y) : ").split(',')
            val = (int(x), int(y))
            self.image.thumbnail(val)
            self.image.save(self.path)
            return True
        except ValueError:
            return False

    def rotate(self):
        try:
            val = int(input("value : "))
            self.image.rotate(val).save(self.path)
            return True
        except ValueError:
            return False

    def blur(self):
        try:
            val = int(input("value : "))
            self.image.filter(ImageFilter.GaussianBlur(radius=val)).save(self.path)
            return True
        except ValueError:
            return False

    def change_brightness(self):
        try:
            val = int(input("value : "))
            enhancer = ImageEnhance.Brightness(self.image)
            enhancer.enhance(val).save(self.path)
            return True
        except ValueError:
            return False

    def change_sharpness(self):
        try:
            val = int(input("value : "))
            enhancer = ImageEnhance.Sharpness(self.image)
            enhancer.enhance(val).save(self.path)
            return True
        except ValueError:
            return False

    def chage_color(self):
        try:
            val = int(input("value : "))
            enhancer = ImageEnhance.Color(self.image)
            enhancer.enhance(val).save(self.path)
            return True
        except ValueError:
            return False

    def change_contrast(self):
        try:
            val = int(input("value : "))
            mod = ImageEnhance.Contrast(self.image)
            mod.enhance(val).save(self.path)
            return True
        except ValueError:
            return False


def image_editor():
    os.system('cls')
    print("1 --> Open/SHOW")
    print("2 --> Resize/Crop")
    print("3 --> ADJUST Brightness")
    print("4 --> ADJUST Sharpness")
    print("5 --> ADJUST Color")
    print("6 --> ADJUST Contrast")
    print("7 --> ADJUST ROTATION")
    print("8 --> Blur")
    print("0 --> MENU")
    inp = input('>>>').strip()

    if inp == '0':
        menu()
    else:
        path = input("image path [ex: C:/img.png]:").strip().strip('"').strip("'")
        image = ImageUtil(path)
        if inp == '1':
            image.open()

        elif inp == '2':
            image.crop()

        elif inp == '3':
            image.change_brightness()

        elif inp == '4':
            image.change_sharpness()

        elif inp == '5':
            image.chage_color()

        elif inp == '6':
            image.change_contrast()

        elif inp == '7':
            image.rotate()

        elif inp == '8':
            image.blur()

        image_editor()


image_editor()