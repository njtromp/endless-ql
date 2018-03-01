from AST.types.type import Type
from PyQt5.QtWidgets import QDoubleSpinBox


class TypeMoney(Type):
    def __init__(self):
        super(TypeMoney, self).__init__()
        self.operations = []

    def __repr__(self):
        return 'money'

    @staticmethod
    def pyqt5_default_widget():
        return QDoubleSpinBox()