set terminal png 10
set encoding utf8
set output "Scenario 2.png"

set  title 'Scenario 2 '

set xrang[0:84]

set ylabel 'nb infectés'
set xlabel 'nb jours'
set key top left
plot "Scenario_2.dat" t"Scenario 2" with lines lt 2 lw 2
