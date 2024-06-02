import matplotlib.pyplot as plot
import numpy as np
from random import randint

file = open("all_time02.5.txt", "r")
totab = open("toTable_time02.5.txt", "w")

size = []
nonindex = []
bree = []
btree_partly = []
hash_ = []

while True:
    line = file.readline()

    if not line:
        break

    num = [float(x) for x in line.split()]
    num[0] = int(num[0])
    num[1] *= 1000
    num[2] *= 1000
    num[3] *= 1000
    num[4] *= 1000

    size.append(num[0])

    nonindex.append(num[1])
    bree.append(num[2])
    btree_partly.append(num[3])
    hash_.append(num[4])

    totab.write(f"\t\t{size[-1]:4d} & {nonindex[-1]:5f} & " +\
                f"\t\t{bree[-1]:4f} & {btree_partly[-1]:5f} & " +\
                f"\t\t{hash_[-1]:5f} " +\
                f" \\\\ \\hline\n")

totab.close()
plot.ylabel("Время (мс)")
plot.xlabel("Количествово записей")
plot.grid(True)


# ==== TIME ====

plot.plot(size, nonindex, color = "blue", label = "Без индекса",
        marker='s', linestyle='-', markersize=4)
plot.plot(size, bree, color = "green", label = "B-tree индекс",
        marker='v', linestyle=':', markersize=4)
plot.plot(size, btree_partly, color = "red", label = "Частичный B-tree индекс",
        marker='*', linestyle='-.', markersize=4)
plot.plot(size, hash_, color = "black", label = "Hash индекс",
        marker='o', linestyle='--', markersize=4)

plot.legend(loc="upper left")

plot.savefig("res_time02.5.pdf")
plot.show()
print("Done")
