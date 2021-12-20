set terminal png 10
set encoding utf8
set output "destributionDegre_lineaire.png"

set  title 'Destribution des degrés  '


set ylabel 'P(k) '
set xlabel 'K'
set key top left
plot "destDEG.dat" t"DBLP linéaire" with linespoints ls 1