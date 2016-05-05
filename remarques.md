* un readme simple pour l'utilisation du projet,
  mais en général on commence par présenter le projet :smirk:
* votre code n'est pas encodé en UTF-8 ? -> j'ai des problèmes d'accents chez moi.
  c'est pas grand chose, mais faites attention à ce genre de détails.
* l'utilisation du paquet `default` c'est mal !
  Vous *DEVEZ* définir un paquet !
* manque de documentation / javadoc.
* l'IHM manque de lisibilité (que représente tel et tel panneau ? à quoi correspond l'arbre ?)
  Dommage car il ne manque pas grand chose pour que ce soit très bien.
* Attention avec votre gestion des chemin !

  ex: `GarbageCollector.java` l25 vous supposez que le séparateur est `/`
  Java devrai être assez conciliant... mais on ne sait jamais.  
