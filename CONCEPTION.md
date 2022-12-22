# Conception du jeu ICRogue (Hiérachie)

health -> 
    Healthbar
    Heart

items -> 
    Coeur
    Coin

level0 ->
    rooms -> 
        Level0BossRoom

# Non Suggérées par L'Énoncé:
    Classe Healthbar : packetage ch.epfl.cs107.play.game.icrogue.actor.health
        est un acteur du jeu au même titre que les items sans posseder d'interactions avec les autres éléments du décor car faisant parti du G.U.I.
        
    Classe Heart : ch.epfl.cs107.play.game.icrogue.actor.health
        est un acteur du jeu utilisé par Healthbar donc dans le même packetage
        
    Classe Coeur : ch.epfl.cs107.play.game.icrogue.actor.items
        est un item collectable par le joueur au même titre que Key et étendant par conséquent Item ayant un impact sur Healthbar à travers le joueur

    Classe Coin : ch.epfl.cs107.play.game.icrogue.actor.items
        est un item collectable par le joueur au même titre que Key et étendant par conséquent Item

    Classe Level0BossRoom : ch.epfl.cs107.play.game.icrogue.area.level0.rooms
        est une room étendant Level0EnemyRoom donc dans le même packetage que celui-ci

# Modifications Personnelles: 
    Dans level0 la méthode setUpConnector nous n'avons pas utiliser la definition de destination ci dessous : 
    DiscreteCoordinates destination = roomCoordinates.jump(connector.getOrientation().toVector());
    Mais décider de faire cela de manière plus rudimentaire, en utilisant les coordonées pour vérifier les cases au alentour,
    car nous les avions déjà. Et nous ne voulions pas complexifié le code avec des getteurs potentiellement intrusifs sur les connecteurs.
    
    Il est possible que nous n'ayons pas suivi à la lettre le tuto mais cela ne représente que de mineur changement si c'est le cas.
    

# Extensions Ajoutées: 
    Barre de vie : présente dans le packetage des actors cette dernières permet de suivre graphiquement la vie du personnage
    
    Coin : Les pièces sont la monnaie du jeu elles sont collectable et apparaissent de manière "pseudo-alééatoire" à la mort des monstres. L'argent du joeur s'affiche à l'aide d'un simple texte
    
    Coeur : Les coeur sont aussi des items car on peut les ramasser et se comportent de la même manière que les pièces a la diférence près que leur apparition peut se faire ("pseudo-aléatoire") une fois les salles d'ennemies résolus

    Level0BossRoom : Cette salle représente la salle du boss elle se place avec les autres salles.


