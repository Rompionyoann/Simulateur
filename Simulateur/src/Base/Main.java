package Base;

public class Main {

	public static void main(String[] args) {
		
		GameWorld world = new GameWorld();
		Fenetre fenetre= new Fenetre(world);
		// reglage de la taille de la fenetre de jeu, en pixels (nb: un écran fullHD =
		// 1980x1050 pixels)
		
		//if(world.isGameCanStart()) {
		StdDraw.setCanvasSize(600,600);
		

		// permet le double buffering, pour type filter textpermettre l'animation
		StdDraw.enableDoubleBuffering();
		
		
		// la boucle principale de notre jeu
		while (world.isGameIsOk()) {
			// Efface la fenetre graphique
			StdDraw.clear();

			// Capture et traite les eventuelles interactions clavier du joueur
			if (StdDraw.hasNextKeyTyped()) {
				char key = StdDraw.nextKeyTyped();
				world.processUserInput(key);
			}

			if (StdDraw.isMousePressed()) {
				world.processMouseClick(StdDraw.mouseX(), StdDraw.mouseY());
			}
			
			if(world.getPausePlay() || fenetre.getCommencer())
			world.step();

			// dessine la carte
			world.dessine();

			// Montre la  graphique mise a jour et attends 20 millisecondes
			StdDraw.show();
			StdDraw.pause(20);

		//}
	}
	}
	}

