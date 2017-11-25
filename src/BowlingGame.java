import java.util.*;

/** BowlingGame Score calculator 
 *
 * @author CDT414 Student: 
 * @version 1.0 
 * @date 2016-11-24
 */
public class BowlingGame {

	/** BowlingGame Score calculator constructor which require string as input 
	 * @param game Expected format "[n,n][n,n]..[n,n]"
	 * 
	 */	 

	List<Frame> gameFrames = new ArrayList<Frame>();
	String game;
	
	public BowlingGame(String game){	
		this.game = game;
	}
	

	
	/** getScore method returns a score of current Bowling game or -1 if error
	 * 
	 * @return Integer value of Bowling score, in case of error return value is -1 
	 */
	public int getScore() {
		if (!verifyStringFormat(this.game) && !verifyLastSpareStringFormat(this.game)
				&& !verifyLastStrikeStringFormat(this.game)) { // Verifiko qe string eshte i vlefshem
			return -1;
		}
		boolean pStrike = false;
		boolean pSpare = false;
		boolean doubleStrike = false;
		int score = 0;
		int i = 0;
		getFrameList(this.game);

		for (Frame f : this.gameFrames) {

			if (i != 10 && !verifyFrameSum(f)) {// Nese shuma eshte me e madhe se 10
				return -1;
			}

			int frameScore = 0;
			frameScore = f.getScore1() + f.getScore2();
			
		
			if (pStrike == true) {
				frameScore = frameScore + f.getScore1();
				if(i != 10) {
				if (doubleStrike) {
					frameScore = frameScore + f.getScore1();
				}
				if (f.isStrike() == true) {
					doubleStrike = true;
				} else {
					doubleStrike = false;
					frameScore = frameScore + f.getScore2();
				}
				}
			} else if (pSpare) {
				frameScore = frameScore + f.getScore1();
			}
		
			score = score + frameScore;
			pStrike = f.isStrike();
			pSpare = f.isSpare();
			i++;

		}
		return score;
	}
	
	public boolean checkStrike(Frame f) {
		if(f.getScore1() + f.getScore2() == 10) {
			if(f.getScore2() == 0) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean checkSpare(Frame f) {
		if(f.getScore1() + f.getScore2() == 10) {
			if(f.getScore2() != 0) {
				return true;
			}
		}
		
		return false;
	}
	
	public int getStandartScore(Frame f){
		return f.getScore1() + f.getScore2();
		
	}
	
	
	public void getFrameList(String game) {
		String[] frameList =  game.split("]");
		
		for(String frame : frameList) {
			frame = frame.substring(1, frame.length());
			String[] scores = frame.split(",");
			
			Frame trueframe = new Frame();
			trueframe.setScore1(Integer.parseInt(scores[0]));
			trueframe.setScore2(Integer.parseInt(scores[1]));
			
			trueframe.setStrike(checkStrike(trueframe));
			trueframe.setSpare(checkSpare(trueframe));
			
			this.gameFrames.add(trueframe);
		}
		
	}
	
	public boolean verifyStringFormat(String game) {
		return game.matches("(\\[([0-9]|10),([0-9]|10)\\]){10}");
	}
	
	public boolean verifyLastStrikeStringFormat(String game) {
		return game.matches("(\\[([0-9]|10),([0-9]|10)\\]){10}\\[([0-9]|10),([0-9]|10)\\]");
	}
	
	public boolean verifyLastSpareStringFormat(String game) {
		return game.matches("(\\[([0-9]|10),([0-9]|10)\\]){10}\\[([0-9]|10)\\]");
	}
	
	public boolean verifyFrameSum(Frame f) {
		if(f.getScore1() + f.getScore2() <= 10) {
			return true;
		} else {
			return false;
		}
	}
	
}
