set terminal png 10
set encoding utf8
set output "Distances_Bal.png"
set  title 'Distribution distances Barabasi-Albert '

set key top right
plot "distancesBAL.dat" t"destribution des distances Barabasi-Albert" with linespoints ls 1