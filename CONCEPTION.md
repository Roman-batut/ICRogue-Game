# Conception du jeu ICRogue (Hiérachie)

health -> 
    Healthbar
    Heart

items -> 
    Coeur
    Coin
    BoostDmgStand
    CoeurStand

level0 ->
    rooms -> 
        Level0BossRoom
        Level0ShopRoom

# Non Suggérées par L'Énoncé:
    Classe Healthbar : packetage ch.epfl.cs107.play.game.icrogue.actor.health
        est un acteur du jeu au même titre que les items sans posseder d'interactions avec les autres éléments du décor car faisant parti du G.U.I.
        
    Classe Heart : ch.epfl.cs107.play.game.icrogue.actor.health
        est un acteur du jeu utilisé par Healthbar donc dans le même packetage
        
    Classe Coeur : ch.epfl.cs107.play.game.icrogue.actor.items
        est un item collectable par le joueur au même titre que Key et étendant par conséquent Item ayant un impact sur Healthbar à travers le joueur

    Classe Coin : ch.epfl.cs107.play.game.icrogue.actor.items
        est un item collectable par le joueur au même titre que Key et étendant par conséquent Item    

    Classe BoostDmgStand : ch.epfl.cs107.play.game.icrogue.actor.items
        est un item collectable par le joueur au même titre que Staff et étendant par conséquent Item à condition d'avoir assez d'argent 

    Classe CoeurStand : ch.epfl.cs107.play.game.icrogue.actor.items
        est un item collectable par le joueur au même titre que Staff et étendant par conséquent Item à condition d'avoir assez d'argent 

    Classe Level0BossRoom : ch.epfl.cs107.play.game.icrogue.area.level0.rooms
        est une room étendant Level0EnemyRoom donc dans le même packetage que celui-ci
    
    Classe Level0ShopRoom : ch.epfl.cs107.play.game.icrogue.area.level0.rooms
        est une room étendant Level0ItemRoom donc dans le même packetage que celui-ci ainsi elle peut contenir une liste d'Item;

# Modifications Personnelles: 
    Dans level0 la méthode setUpConnector nous n'avons pas utiliser la definition de destination ci dessous : 
    DiscreteCoordinates destination = roomCoordinates.jump(connector.getOrientation().toVector());
    Mais décider de faire cela de manière plus rudimentaire, en utilisant les coordonées pour vérifier les cases au alentour,
    car nous les avions déjà. Et nous ne voulions pas complexifié le code avec des getteurs potentiellement intrusifs sur les connecteurs.

    Lors de la création de projectile de feu il décide lui même de ses dégats de feu cela nous à permis d'éviter les setteurs et getteurs intrusifs
    
    Il est possible que nous n'ayons pas suivi à la lettre le tuto mais cela ne représente que de mineur changement si c'est le cas.


    

# Extensions Ajoutées: 
    Barre de vie : présente dans le packetage des actors cette dernières permet de suivre graphiquement la vie du personnage
    
    Coin : Les pièces sont la monnaie du jeu elles sont collectable et apparaissent de manière "pseudo-alééatoire" à la mort des monstres. L'argent du joeur s'affiche à l'aide d'un simple texte
    
    Coeur : Les coeur sont aussi des items car on peut les ramasser et se comportent de la même manière que les pièces a la diférence près que leur apparition peut se faire ("pseudo-aléatoire") une fois les salles d'ennemies résolus

    Level0BossRoom : Cette salle représente la salle du boss elle se place avec les autres salles.

    Level0ShopRoom : Cette salle à un marchand qui permet d'acheter différent boost utile pour les salles d'ennemies 

    Marchand: simplement un pnj qui prends une case
   
    CoeurStand et BoostDmgStand : ces deux objets sont non traversables et sont présents dans le shop avec un certain prix(impossible d'acheter sans assez d'argent)


