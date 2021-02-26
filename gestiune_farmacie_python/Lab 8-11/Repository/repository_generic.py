import pickle
from Domain.entity import *
from Repository.repository_exception import Repository_exception


class Repository_generic():
    def __init__(self, filename):
        self.__storage = {}
        self.__filename = filename
        self.__load_file()

    def __load_file(self):
        try:
            with open(self.__filename, 'rb') as f:
                self.__storage = pickle.load(f)
        except:
            print('Fisier invalid')

    def __write_file(self):
        try:
            with open(self.__filename, 'wb') as f:
                pickle.dump(self.__storage, f)
        except:
            print('Fisier invalid')

    def add(self, entity):
        self.__load_file()
        id = entity.get_id()
        if id in self.__storage:
            raise Repository_exception('ID DUPLICAT')
        self.__storage[id] = entity
        self.__write_file()

    def delete(self, id):
        self.__load_file()
        if id in self.__storage:
            del (self.__storage[id])
            self.__write_file()
        else:
            raise Repository_exception('NU EXISTA')
        self.__write_file()

    def update(self, entity):
        self.__load_file()
        id = entity.get_id()
        if id in self.__storage:
            self.__storage[id] = entity
            self.__write_file()
        else:
            raise Repository_exception('NU EXISTA')
        self.__write_file()

    def read(self, id=None):
        self.__load_file()
        if id is None:
            return self.__storage.values()
        if id in self.__storage:
            return self.__storage[id]
        else:
            raise Repository_exception('NU EXISTA')
