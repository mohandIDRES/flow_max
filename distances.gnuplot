set terminal png 10
set encoding utf8
set output "Distances.png"
set  title 'Distribution distances  '

set key top right
plot "distances.dat" t"destribution des distances" with linespoints ls 1