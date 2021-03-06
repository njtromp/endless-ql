from AST.types.type import Type
from render.widgets import DoubleSpinBox


class TypeMoney(Type):
    def __init__(self):
        super(TypeMoney, self).__init__()
        self.operations = []

    def __repr__(self):
        return 'money'

    def __eq__(self, other):
        return type(self) == type(other)

    @staticmethod
    def pyqt5_default_widget():
        return DoubleSpinBox()
