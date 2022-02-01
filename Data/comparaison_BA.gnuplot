set terminal png 10
set encoding utf8
set output "comparaison_BA.png"

set  title 'Comparaison des deux scenarios Barabàsi-Albert  '

set xrang[0:84]

set ylabel 'nb infectés'
set xlabel 'nb jours'
set key top left
plot "Scenario_BA.dat" t"Scenario 3 Barabàsi-Albert" with lines lt 1 lw 2,"Scenario_BA_SC2.dat" t"Scenario 2 Barabàsi-Albert" with lines lt 2 lw 2