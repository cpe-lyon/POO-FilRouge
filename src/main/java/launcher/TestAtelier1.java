package launcher;

import model.cards.Card; 
import model.cards.Hand;
import model.cards.Rank;
import model.cards.Suit;
import model.player.Player;
import view.ConsoleTui;

/**
 * Programme de test des classes du Model pour l'atelier 1
 * La trace d'exécution attendue est écrite en commentaire de chaque instruction
 *
 * @author francoise.perrin
 */
public class TestAtelier1 {

	private static final ConsoleTui TUI = new ConsoleTui();

	public static void main(String[] args) {

		TUI.title("Test Atelier 1", "Validation progressive des classes Card, Hand et Player");


		//////////////////////////////////
		// Test classe Card
		//////////////////////////////////

		TUI.section("Test classe Card");
		Card c1;
		c1 = new Card(Rank._2, Suit.CARREAU);
		Card c2 = new Card(Rank._ROI, Suit.CARREAU);
		Card c3 = new Card(Rank._2, Suit.CARREAU);
		Card c4 = new Card(Rank._2, Suit.PIQUE);
		Card c2Bis = c2;


		/* Test toString() */
		TUI.check("c1", c1.toString(), "?-?");
		TUI.check("c2", c2, "?-?");


		/* Test reveale(), hide(), isRevealed */
		c1.reveale();
		TUI.check("c1", c1, "2-Carreau");
		TUI.check("c1.isRevealed()", c1.isRevealed(), "true");
		c1.hide();
		TUI.check("c1 apres hide()", c1, "?-?");
		TUI.check("c1.isRevealed()", c1.isRevealed(), "false");

		/* Test différence Objet et Référence */
		c2.reveale();
        TUI.check("c2 apres reveale()", c2, "Roi-Carreau");
        c2Bis.hide();
		TUI.check("c2 apres hide depuis c2Bis", c2, "?-?");


		/* Test getSuit() */
		TUI.check("c1.getSuit()", c1.getSuit(), "CARREAU");


		/* Test getRank() */
		TUI.check("c1.getRank()", c1.getRank(), "_2");
		TUI.check("c2.getRank()", c2.getRank(), "_ROI");
		TUI.check("c1.getRank().getRank()", c1.getRank().getRank(), "2");
		TUI.check("c2.getRank().getRank()", c2.getRank().getRank(), "13");
		TUI.check("c2.getRank().getName()", c2.getRank().getName(), "Roi");


		/* Test ==, equals() + hashCode() */
		TUI.check("c1 == c1", (c1 == c1), "true");
		TUI.check("c1 == c3", (c1 == c3), "false");

		// Testez avec méthodes equals() et hashCode() commentées dans classe card [ c1.equals(c3) : false ]
		// recommencez le test après suppression des commentaires [ c1.equals(c3) : true ]
		TUI.note("Note", "Le resultat de c1.equals(c3) depend de l'implementation de equals() et hashCode().");
		TUI.check("c1.equals(c1)", c1.equals(c1), "true");
		TUI.check("c1.equals(c3)", c1.equals(c3), "false / true");


		/* Test compareTo() */
		TUI.check("c1.compareTo(c1)", c1.compareTo(c1), "0");
		TUI.check("c1.compareTo(c2)", c1.compareTo(c2), "-11");
		TUI.check("c3.compareTo(c1)", c3.compareTo(c1), "0");
		TUI.check("c3.compareTo(c4)", c3.compareTo(c4), "0");



		//////////////////////////////////
		// Test classe Hand
		//////////////////////////////////

		TUI.section("Test classe Hand");
		Hand hand = new Hand();

		/* Test toString() */
		TUI.info("hand - toString()", hand);
		// Décommentez la méthode toString() de la classe Hand
		TUI.check("hand", hand, "[[]]");


		/* Test addCard() */
		c1.reveale();
		c2.reveale();
		c3.reveale();
		c4.reveale();
		hand.addCard(c1);
		TUI.check("hand", hand, "[[2-Carreau]]");
		hand.addCard(c2);
		hand.addCard(c3);
		hand.addCard(c4);
		TUI.check("hand", hand, "[[2-Carreau, Roi-Carreau, 2-Carreau, 2-Pique]]");


		/* Test isEmpty(), size() */
		TUI.check("hand.size()", hand.size(), "4");
		TUI.check("hand.isEmpty()", hand.isEmpty(), "false");


		/* Test removeTopCard(), removeCard(), clear() */
		hand.removeTopCard();
		TUI.check("Apres removeTopCard()", hand, "[[Roi-Carreau, 2-Carreau, 2-Pique]]");
		hand.removeCard(1);
		TUI.check("Apres removeCard(1)", hand, "[[Roi-Carreau, 2-Pique]]");
		hand.removeCard(6);
		TUI.check("Apres removeCard(6)", hand, "[[Roi-Carreau, 2-Pique]]");
		hand.clear();
		TUI.check("Apres clear()", hand, "[[]]");
		TUI.check("hand.isEmpty()", hand.isEmpty(), "true");
		hand.removeTopCard();
		TUI.check("Apres clear + removeTopCard()", hand, "[[]]");
		hand.removeCard(1);
		TUI.check("Apres clear + removeCard(1)", hand, "[[]]");


		/* Test hideCard() */
		hand.addCard(c1);
		hand.addCard(c2);
		TUI.check("hand", hand, "[[2-Carreau, Roi-Carreau]]");
		hand.hideCard(0);
		TUI.check("Apres hideCard(0)", hand, "[[?-?, Roi-Carreau]]");
		hand.hideCard(1);
		TUI.check("Apres hideCard(1)", hand, "[[?-?, ?-?]]");
		hand.hideCard(6);
		TUI.check("Apres hideCard(6)", hand, "[[?-?, ?-?]]");


		/* Test revealedCard() */
		hand.revealeCard(1);
		TUI.check("Apres revealeCard(1)", hand, "[[?-?, Roi-Carreau]]");
		hand.revealeCard(6);
		TUI.check("Apres revealeCard(6)", hand, "[[?-?, Roi-Carreau]]");
		TUI.check("Apres revealeCard(0)", hand, "[[2-Carreau, Roi-Carreau]]");


		/* Test playCard() */
		Card c5 = hand.playCard(0);	// playCard rend visible la carte et la supprime de la List
		TUI.check("c5", c5, "2-Carreau");
		TUI.check("Apres playCard(0)", hand, "[[Roi-Carreau]]");
		Card c6 = hand.playCard(6);	// playCard rend visible la carte et la supprime de la List
		TUI.check("c6", c6, "null");
		TUI.check("Apres playCard(6)", hand, "[[Roi-Carreau]]");


		/* Tests playCard(), revealedCard(), hideCard() si List vide */
		hand.clear();
		Card c7 = hand.playCard(0);
		TUI.check("c7", c7, "null");
		TUI.check("Apres clear + playCard(0)", hand, "[[]]");
		hand.hideCard(1);
		TUI.check("Apres clear + hideCard(1)", hand, "[[]]");
		hand.revealeCard(1);
		TUI.check("Apres clear + revealeCard(1)", hand, "[[]]");
		TUI.check("Apres clear + isEmpty()", hand.isEmpty(), "true");


		//////////////////////////////////
		// Test classe Player
		//////////////////////////////////

		TUI.section("Test classe Player");
		Player p1 = new Player("Joueur1");


		/* Test toString(), getname() */
		TUI.check("p1", p1, "[Joueur1 ** Hand[[]] ** trickPile[[]]]");
		TUI.check("p1.getName()", p1.getName(), "Joueur1");


		/* Test setTrickWinner() isTrickWinner(), setGameWinner() isGameWinner() */
		p1.setTrickWinner(true);
		TUI.check("p1.isTrickWinner()", p1.isTrickWinner(), "true");
		p1.setGameWinner(true);
		TUI.check("p1.isGameWinner()", p1.isGameWinner(), "true");


		/* Test addCardToHand(), addCardToTrickPile() */
		p1.addCardToHand(c1);
		TUI.check("p1", p1, "[Joueur1 ** Hand[[2-Carreau]] ** trickPile[[]]]");
		p1.addCardToTrickPile(c2);
		p1.addCardToTrickPile(c4);
		TUI.check("p1", p1, "[Joueur1 ** Hand[[2-Carreau]] ** trickPile[[Roi-Carreau, 2-Pique]]]");


		/* Test hasWonAllCards(), isHandEmpty(), isTrickPileEmpty(), isStillActive() */
		TUI.check("p1.isHandEmpty()", p1.isHandEmpty(), "false");
		TUI.check("p1.isTrickPileEmpty()", p1.isTrickPileEmpty(), "false");
		TUI.check("p1.isStillActive()", p1.isStillActive(), "true");
		TUI.check("p1.hasWonAllCards(3)", p1.hasWonAllCards(3), "true");
		TUI.check("p1.hasWonAllCards(6)", p1.hasWonAllCards(6), "false");


		/* Test hideCard(), revealeCard(), playCard() */
		p1.hideCard(0);
		TUI.check("Apres hideCard(0)", p1, "[Joueur1 ** Hand[[?-?, Roi-Carreau, 2-Pique]] ** trickPile[[]]]");
		p1.revealeCard(0);
		TUI.check("Apres revealeCard(0)", p1, "[Joueur1 ** Hand[[2-Carreau, Roi-Carreau, 2-Pique]] ** trickPile[[]]]");
		Card c8 = p1.playCard(0);
		TUI.check("Apres playCard : c8", c8, "2-Carreau");
		TUI.check("Apres playCard(0)", p1, "[Joueur1 ** Hand[[Roi-Carreau, 2-Pique]] ** trickPile[[]]]");

		/* Test removeCardFromHand(), removeCardFromTrickPile() */
		p1.addCardToTrickPile(p1.removeCardFromHand(0));
		TUI.check("Apres removeCardFromHand(0)", p1, "[Joueur1 ** Hand[[Roi-Carreau]] ** trickPile[[2-Pique]]]");
		p1.removeCardFromTrickPile(0);
		TUI.check("Apres removeCardFromTrickPile(0)", p1, "[Joueur1 ** Hand[[2-Pique]] ** trickPile[[]]]");


		/* Test equals(), compareTo */
		// faites générer les méthodes equals et hashcode par votre IDE
		TUI.check("p1.equals(new Player(\"Joueur2\"))", p1.equals(new Player("Joueur2")), "false");
		TUI.check("p1.compareTo(p1)", p1.compareTo(p1), "0");
		TUI.check("new Player(\"Joueur3\").compareTo(p1)", new Player("Joueur3").compareTo(p1), "2");
		
	
	}

}


