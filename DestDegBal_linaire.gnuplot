set terminal png 10
set encoding utf8
set output "destributionDegre_BAL_lineaire.png"

set  title 'Destribution des degrés Barabasi-Albert '


set ylabel 'P(k) '
set xlabel 'K'
set key top left
plot "destDEGBAL.dat" t"DBLP linéaire"