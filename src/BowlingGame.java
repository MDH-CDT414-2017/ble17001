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

	List<Frame> game1 = new ArrayList();
	
	public BowlingGame(String game)
	{	
		String[] frameList =  game.split("]");
		
		for(String frame : frameList) {
			frame = frame.substring(1, frame.length());
			String[] scores = frame.split(",");
			
			Frame trueframe = new Frame();
			trueframe.setScore1(Integer.parseInt(scores[0]));
			trueframe.setScore2(Integer.parseInt(scores[1]));
			
			trueframe.setStrike(checkStrike(trueframe));
			trueframe.setSpare(checkSpare(trueframe));
			
			
			this.game1.add(trueframe);
			


		}
		
		
	}
	
	/** getScore method returns a score of current Bowling game or -1 if error
	 * 
	 * @return Integer value of Bowling score, in case of error return value is -1 
	 */
	public int getScore() {
		//TODO: calculate the score of game and return correct value
		boolean pStrike = false;
		boolean pSpare = false;
		boolean doubleStrike = false;
		int score = 0;
		for(Frame f: game1) {
			int frameScore = 0;

			frameScore = f.getScore1() + f.getScore2();
			if(pStrike == true) {
				frameScore = frameScore + f.getScore1();
				
				if(doubleStrike) {
					frameScore = frameScore + f.getScore1();
				}
				
				if(f.isStrike() == true) {
					doubleStrike = true;
				} else {
					doubleStrike = false;
					frameScore = frameScore + f.getScore2();
				} 
			} else if(pSpare) {
				frameScore = frameScore + f.getScore1();
			}
			
			score = score + frameScore;
			
			pStrike = f.isStrike();
			pSpare = f.isSpare();

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
	
}
