import java.util.*;

/** BowlingGame Score calculator 
 *
 * @author CDT414 Student: Bora Lamce
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
				&& !verifyLastStrikeStringFormat(this.game)) {
			return -1;
		}
		boolean pStrike = false;
		boolean pSpare = false;
		boolean doubleStrike = false;
		int score = 0;
		int i = 0;
		getFrameList(this.game);
		
		if (!verifyLastFrame(this.gameFrames)) {
			return -1;
		}
		
		
		for (Frame f : this.gameFrames) {

			if (i < 10 && !verifyFrameSum(f)) {
				return -1;
			}

			int frameScore = 0;
			if (i < 10) {
				frameScore = f.getScore1() + f.getScore2();

				if (pStrike == true) {
					frameScore = frameScore + f.getScore1();
					if (doubleStrike) {
						frameScore = frameScore + f.getScore1();
					}
					if (f.isStrike() == true) {
						doubleStrike = true;
					} else {
						doubleStrike = false;
						frameScore = frameScore + f.getScore2();
					}
				} else if (pSpare) {
					frameScore = frameScore + f.getScore1();
				}
			} else {
				frameScore = f.getScore1() + f.getScore2();
				if(doubleStrike) {
					frameScore = frameScore + f.getScore1();
				}
			}
			score = score + frameScore;
			pStrike = f.isStrike();
			pSpare = f.isSpare();
			i++;

		}
		return score;
	}
	
	public boolean checkStrike(Frame f) {
		if((f.getScore1() == 10) && f.getScore2() == 0) {
				return true;
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
	
	
	
	public void getFrameList(String game) {
		String[] frameList =  game.split("]");
		
		for(String frame : frameList) {
			frame = frame.substring(1, frame.length());
			String[] scores = frame.split(",");
			
			Frame trueframe = new Frame();
			trueframe.setScore1(Integer.parseInt(scores[0]));
			if(scores.length == 2) {
			trueframe.setScore2(Integer.parseInt(scores[1]));
			}
			
			trueframe.setStrike(checkStrike(trueframe));
			trueframe.setSpare(checkSpare(trueframe));
			
			this.gameFrames.add(trueframe);
		}
		
	}
	
	public boolean verifyStringFormat(String game) {
		if (game != null) {
			return game.matches("(\\[([0-9]|10),([0-9]|10)\\]){10}");
		}
		return false;
	}
	
	public boolean verifyLastStrikeStringFormat(String game) {
		if (game != null) {
			return game.matches("(\\[([0-9]|10),([0-9]|10)\\]){10}\\[([0-9]|10),([0-9]|10)\\]");
		}
		return false;
	}

	public boolean verifyLastSpareStringFormat(String game) {
		if (game != null) {
			return game.matches("(\\[([0-9]|10),([0-9]|10)\\]){10}\\[([0-9]|10)\\]");
		}
		return false;
	}
	
	public boolean verifyFrameSum(Frame f) {
		if(f.getScore1() + f.getScore2() <= 10) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean verifyLastFrame(List<Frame> gameFrames){
		
		if(verifyStringFormat(this.game)){
			Frame lastFrame = gameFrames.get(9);
			if(!lastFrame.isStrike() && !lastFrame.isSpare()){
				return true;
			} 
		} else if (verifyLastSpareStringFormat(this.game)){
			Frame beforeLastFrame = gameFrames.get(9);
			if(beforeLastFrame.isSpare()){
				return true;
			} 
		} else {
			Frame beforeLastFrame = gameFrames.get(9);
			if(beforeLastFrame.isStrike()){
				return true;
			} 
		}
		
		return false;
		
	}
	
}
