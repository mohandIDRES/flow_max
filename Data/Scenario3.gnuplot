set terminal png 10
set encoding utf8
set output "Scenario 3.png"

set  title 'Scenario 3 '

set xrang[0:84]

set yrang[0:6]

set ylabel 'nb infectés'
set xlabel 'nb jours'
set key top left
plot "Scenario_3.dat" t"Scenario 3"  with lines lt 2 lw 2
