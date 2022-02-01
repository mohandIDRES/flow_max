set terminal png 10
set encoding utf8
set output "BA_scenario_2.png"

set  title 'Simulation scenario 2 Barabàsi-Albert  '

set xrang[0:84]

set ylabel 'nb infectés'
set xlabel 'nb jours'
set key top left
plot "Scenario_BA_SC2.dat" t"Scenario 2 Barabàsi-Albert" with lines lt 2 lw 2