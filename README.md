# MesuresDeRéseauDInteraction

Apres avoir télécharger le fichier des données , on a utilisé GraphStream pour lire ces données .
**Question 1** :
Pour cette question on utilise des fonctions de la classe **Toolkit**
- **Nombre de noeuds**

Pour calculer le nombre de noeuds on utilise la fonction getNodeCount() . </br>
Le nombre de noeuds du graphe est  : **317080**

- **le degré moyen**
pour calculer le degré moyen on utilise la méthode averageDegree(graph).</br>
Le degré moyen du graphe est : **6.62208890914917**

- **Coefficient de clustering**
on utilise la methode averageClusteringCoefficient(graph) .</br>
Le coefficient de clustering :**0.6324308280637396**

- **Coefficient de clustering pour un reseau aleatoire**
Le coefficient de clustering d'un réseau quelconque est le degré moyen `<K>` / le nombre de noeuds `N`. </br>
pour un graphe de même degré et de même taille , le coefficient de clustering est  : **2.0884599814397534E-5**

**Question 2** :

1- Un réseau connex est un réseau dont à partir d'un sommet on peut visiter n'importe quel autre sommet du réseau .
une méthode de la bibliothéque ToolKit nous permet de savoir si un graphe est connex ou pas , c'est la méthode isConnected(graph) .
2- Pour q'un réseau aleatoire soit connex , il faut vérifier que le degré moyen `<K>` est superieur au Ln(nombre de noeud du graphe `N`) , c'est-à-dire `<k> > ln(N)` .</br>
Donc pour un réseau aléatoire de même taille et même degré (ce qui fait `<k> = 6.62208890914917 ` et `
ln(N) = 12.666909387`) , on constate que `〈k〉< ln(N)` , donc le réseau n'est pas connex .

Un réseau aleatoire de même taille est connex si seulement si le degré moyen de ce réseau est superieur à 12.666909387.
