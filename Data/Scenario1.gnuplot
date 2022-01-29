set terminal png 10
set encoding utf8
set output "Scenario 1.png"

set  title 'Scenario 1 '

set xrang[0:84]

set ylabel 'nb infectés'
set xlabel 'nb jours'
set key top left
plot "Scenario_1.dat" t"Scenario 1"
