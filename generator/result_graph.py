import matplotlib.pyplot as plot
import numpy as np
from random import randint

file = open("times.txt", "r")
totab = open("toTable_1vs8.txt", "w")

size = []
index = []
nonindex = []

while True:
    line = file.readline()

    if not line:
        break

    num = [float(x) for x in line.split()]
    num[0] = int(num[0])
    #num[1] = int(num[1] / 1e0)
    #num[2] = int(num[2] / 1e0)

    size.append(num[0])
    index.append(num[1])
    nonindex.append(num[2])

    totab.write(f"\t\t{size[-1]:4d} & {index[-1]:5f} & " +\
                f"\t\t{nonindex[-1]:5f} " +\
                f" \\\\ \\hline\n")

totab.close()
plot.ylabel("Время (с)")
plot.xlabel("Кол-во записей, ед")
plot.grid(True)

#linestyle='--'
plot.plot(size, index, color = "blue", label = "С индексом",
        marker='s', linestyle='-.', markersize=6)
plot.plot(size, nonindex, color = "green", label = "Без индекса",
        marker='v', linestyle=':', markersize=6)

plot.legend(loc="upper left")

plot.savefig("zres_1vs8.png")
plot.show()
print("Done")
