set terminal png 10
set encoding utf8
set output "BA_scenario3.png"

set  title 'Simulation scenario 3 Barabàsi-Albert  '

set xrang[0:84]

set ylabel 'nb infectés'
set xlabel 'nb jours'
set key top left
plot "Scenario_BA.dat" t"Scenario Barabàsi-Albert" with lines lt 1 lw 2