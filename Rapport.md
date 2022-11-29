# Rendu CLASSIFICATION pour la SAÉ S3.02

# Membres :

* Malak Hayar
* Léa Bedrici
* Charles Top-Pottez
* Gwendal Margely


## Trois fonctionnalités : 

L'interface contient un curseur avec trois options possibles : **Dataset, Classifieur, Robustesse**

## Analyse des données : Dataset

### Présentation des types de données  

Les deux types de datasets couvert par notre application sont *Iris* et *Titanic*. Chaque ligne de ce dataset est un point, représenté une interface commune à tout les deux qui est *IPOINT*, puis plus particulièrement par un object de leur nom. On accède à un de leur attribut à travers une instance générique appelé *Column*, qui agit comme conteneur de l'attribut. 

#### IRIS 

Iris est un type de point qui contient uniquement des valeurs numériques, qu'on a représenté avec des types doubles, ainsi qu'un enum pour sa **variété** : sa *classe*.

#### TITANIC

Titanic(passenger) est un type de point qui contient des valeurs *numériques* (parch, sibSp..) représenté par des integer et des doubles, des valeurs *énumérées* (Embarked, Gender), des *String* (ticket, Cabin..) ainsi qu'un boolean pour sa **survie** : sa *classe*.

### Normalisation 

On utilise une interface *IValueNormalizer* contenant deux fonctions *Normalize* et *denormalize* qu'on implémentera dans plusieurs classes spécifiques à un type de données, suivant un **patron stratégie**. On a bien dit *type de données* donc ce sont des implémentation génériques, pouvant marcher pour l'un comme pour l'autre. 

***Note de début*** : *Le type de normalisation utilisé pour l'enum serait plus pertinant si on avait des enum croissant, ce qui n'est pas le cas ici, mais *Monsieur Delecroix* nous a dit de quand même garder cette classe (et on fait tout ce qu'il dit évidemment.)

#### NumberNormalizer

Ce normalizer prend en paramètre la valeur numérique maximale et minimale de la colonne, puis normalise la valeur donnée en paramètre en utilisant le calcul suivant : 

$$(value - min) / (max - min)$$

Ce qui donne un valeur entre 0 et 1.

pour le denormaliser, on utilise un calcul inverse :

$$value * (max-min)+min$$

Ce qui donne la valeur de base.

#### EnumNormalizer 

Ce normalizer a été implémenté de sorte à ce qu'il puisse prendre en compte n'importe quel type d'enum, pour cela il prend en paramètre la classe de l'enum (<code>Class<? extends Enum></code>) 

Pour normaliser un enum donné, on récupère son ordinal et le nombre d'élements qu'il y'a dans tout l'enum et on effectue le calcul suivant : 

$$EnumOrdinal / (NbrElementsInEnum - 1)$$

Ce qui nous donne une valeur entre 0 et 1 pondérée en fonction du nombre de valeurs qu'il y'a dans l'enum. 

Pour dénormaliser la valeur obtenue, on utilise une méthode <code>castByOrdinal</code> (voir code) qui renvoi l'enum correspondant à l'ordinal passé en paramètre. 

Le calcul suivant nous permet d'obtenir cet ordinal à partir de la valeur normalisée : 

$$ordinal = value * (NbrElementsInEnum - 1)$$

on passe enfin l'ordinal dans la fonction comme il ceci : 

<code>CastByOrdinal(ordinal)</code>

Comme expliqué plus haut, on a pas dans nos dataSet des enum croissant (exemple : *PETIT, MOYEN, GRAND*). Donc la **normalisation finale** sera de retourner 0 si la différences des valeurs normalisés est de 0 et 1 si elle différente de 0.

#### BooleanNormalizer :

Ici la normalisation est facile, on normalise un boolean en renvoyant 1 s'il est *true* et 0 s'il *false*, et l'inverse pour la dénormalisation. 

### Distances 

Les distances utilisées sont la distance *euclidienne* et de *manhatthan*, elle sont implémentées à partir d'une interface fonctionnelle contenant une seule méthode <code>CalculateDistance</code>

Cette fonction prend en paramètre les deux points à classifier, *le dataSet étiquetté (avec les classes)*, à partir de celui ci on pourra classifier l'autre *dataSet* en paramètre. 

l'algorithme utilisé pour chaque distance possède à peu près la même structure mais diffère dans la formule mathématique utilisée. On itère sur chaque attribut du point duquel on obtient sa valeur normalisé et on applique cette formule dessus

#### Distance Euclidienne 

La formule est la suivante : 

$$d_M(point_1,point_2)= | point_1.normalizedAttr_1 - point_2.normalizedAttr_1 | + | point_1.normalizedAttr_2 - point_2.normalizedAttr_2 | + ... + | point_1.normalizedAttr_N - point_2.normalizedAttr_N |$$

#### Distance de Manhatthan 

La formule est la suivante : 

$$ res = (point_1.normalizedAttr_1 - point_2.normalizedAttr_1)^2 + (point_1.normalizedAttr_2 - point_2.normalizedAttr_2)^2 + ... (point_1.normalizedAttr_N - point_2.normalizedAttr_N)^2$$

$$d_E(point_1, point_2) = \sqrt{res}$$

## Implémentation de k-NN : Classifieur


L'implémentation de K-NN a été fait dans une classe *Classifier* qui utilise toute l'implémentation des différentes distances. 

Le code est divisé en deux méthodes principales : **closeNeighbours** et **classify**

### closeNeighbours : 

Cette fonction prend en paramètre le point à classifier, le nombre $K$ choisi, ainsi que la distance choisie (Euclidienne/manhatthan) 

On commence par vérifier si le $k$ passé en paramètre est supérieur ou pas au nombre de points présents dans le dataSet, si oui, il sera automatiquement égal à $NbLines - 1$.

On instancie une HashMap de *IPoints* avec leur *distances* par rapport au point *p* correspondantes, puis une liste contenant tous les points du dataSet moins le point à classifier (pour pas qu'il fausse nos calcul, le réel plus proche point d'un point est lui même).

On itère sur cette liste et on *put* le point avec la distance qui le sépare du point *p*. Puis on utilise **stream** pour obtenir les $K$ point avec les distance les moins élevés, donc les plus proche voisin du point *p*.

### Classify 

La méthode *classify* prend en paramètre le point à classifier, ainsi que la liste de ses plus proche voisins. 

On instancie une hashMap contenant des enum GenericClass en clé avec un compteur de leur occurence attribuée à chacune comme valeur.    

On itère sur la liste des closes neighnour et on incrémente le compteur de la classe générique correspondante 

On prend enfin la classe avec la valeur la plus élevée, si on ne peut pas départager, on prend la classe du voisin le plus proche.

Si le point n'avait pas de classe on lui donne cette classe, sinon on ne le fait pas pour pas fausser le résultat

La méthode renvois enfin un boolean indiquant si la classe du point (s'il en possédait une au départ) était la même à celle obtenue par le classifier ou non.    


## Solidité du modèle : Robustesse

Le protocole utilisé pour la robustesse est un algorithme itérant sur tout le dataset, puis calcule pour chaque point ses plus proches voisins et l'applique la fonction classify dessus, si elle renvoit true *(la classe de base du point est égale à celle donnée par le classifieur)* alors on incrémente un compteur.

A la fin, on obtient le pourcentage de robustesse en effectuant le calcul suivant :

$$cpt * 100 / NbrLignesDuDataSet$$

On a effectué des scénario d'éxécution dans nos classes test, donc voir les tests relatifs au **classifier**. 
