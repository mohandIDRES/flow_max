set terminal png 10
set encoding utf8
set output "Comparaison.png"

set  title 'Comparaison des3 scénarios  '

set xrang[0:84]

set ylabel 'nb infectés'
set xlabel 'nb jours'
set key top left
plot "Scenario_1.dat" t"Scenario 1" with lines lt 1 lw 2, "Scenario_2.dat" t"Scenario 2" with lines lt 2 lw 2 ,"Scenario_3.dat" t"Scenario 3" with lines lt 3 lw 5
