'''dict={'key1':1,
      'key2': 2}
print(dict)
copie = {}
for key in dict:
    copie[key] = dict[key]
print(copie)


print(dict['key1'])
del dict["key1"]
print(dict)

dict.pop('key2')
print(dict)

dict={'key1':1,
      'key2': 2}
for id in dict:
    print(id)
#id = input('id=')
if id in dict:
    print('da')

dict.pop('key1')
dict.pop('key2')
print(dict)

from Domain.cerc import Cerc
cerculet = Cerc(1, 0, 0, 9)
cerculet2 = Cerc(2, 0, 0, 9)
print(cerculet)
dict = {}
copie={}

dict[cerculet.get_id()] = cerculet
dict[cerculet2.get_id()] = cerculet2
for key in dict:
    copie[key]=dict[key]
print(dict)
print(copie)
list =[]
list.append(cerculet)
print(list)
import json
def write_file():
    f = open('file', 'w')
    f.write('aici')
    f.close()
write_file()
x='aicia'
def save_in_file(x):
    with open('file', 'w') as f_out:
        f_out.write(json.dumps(x))

save_in_file(x)

def read():
    with open('file', 'r+', encoding = 'utf-8') as f:
        print(f.readline(), end = '')

read()

string = '123456'
C=int(len(string)/2)
print(string[:-C])

def read():
    with open ('D:\Python\PyCharm\lab10.1\CERC.txt', 'r') as f:
        return f.read()

print(read())
string = read()
components = string.split('\n')
print(components)
dict = {}
for cerc in components:
    propr_cerc=cerc.split(',') #primul cerc
    print(propr_cerc)
    id_string = propr_cerc[0].split(':')
    id = int(id_string[1])
    raza_string = propr_cerc[3].split(':')
    raza = float(raza_string[1])
    x_centru_string = propr_cerc[1].split(':')
    x_centru = float(x_centru_string[2])
    y_centru_string = propr_cerc[2].split(':')
    y_centru = float(y_centru_string[1][:-1])
    #print(id,' ',x_centru,' ', y_centru, ' ', raza)'''
