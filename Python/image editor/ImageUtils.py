from PIL import Image
from PIL import ImageEnhance
from PIL import ImageFilter


class ImageUtil:
    def __init__(self, path) -> None:
        self.path = path
        self.image = Image.open(self.path)

    def open(self):
        self.image.show()

    def crop(self):
        try:
            x, y = input("ENTER THE VALUES (X,Y) : ").split(',')
            val = (int(x), int(y))
            self.image.thumbnail(val)
            self.image.save(self.path)
            return True
        except ValueError:
            return False

    def rotate(self):
        try:
            val = int(input("ENTER THE VALUE : "))
            self.image.rotate(val).save(self.path)
            return True
        except ValueError:
            return False

    def blur(self):
        try:
            val = int(input("ENTER THE VALUE : "))
            self.image.filter(ImageFilter.GaussianBlur(radius=val)).save(self.path)
            return True
        except ValueError:
            return False

    def change_brightness(self):
        try:
            val = int(input("ENTER THE VALUE : "))
            enhancer = ImageEnhance.Brightness(self.image)
            enhancer.enhance(val).save(self.path)
            return True
        except ValueError:
            return False

    def change_sharpness(self):
        try:
            val = int(input("ENTER THE VALUE : "))
            enhancer = ImageEnhance.Sharpness(self.image)
            enhancer.enhance(val).save(self.path)
            return True
        except ValueError:
            return False

    def chage_color(self):
        try:
            val = int(input("ENTER THE VALUE : "))
            enhancer = ImageEnhance.Color(self.image)
            enhancer.enhance(val).save(self.path)
            return True
        except ValueError:
            return False

    def change_contrast(self):
        try:
            val = int(input("ENTER THE VALUE : "))
            mod = ImageEnhance.Contrast(self.image)
            mod.enhance(val).save(self.path)
            return True
        except ValueError:
            return False
