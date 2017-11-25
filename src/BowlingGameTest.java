/** BowlingGameTest 
 *
 * @author CDT414 Student:
 * @version 1.0 
 * @date 2016-11-24
 */
import junit.framework.TestCase;

/** BowlingGame Score calculator test cases 
 *  
 */	 
public class BowlingGameTest extends TestCase {
        
	    
	public void testClassic() {
        BowlingGame bowlingGame = new BowlingGame("[1,5][3,6][7,2][3,6][4,4][5,3][3,3][4,5][8,1][2,6]");
        
        assertEquals(81, bowlingGame.getScore());
    }	
	
	public void testStrike() {
        BowlingGame bowlingGame = new BowlingGame("[10,0][3,6][7,2][3,6][4,4][5,3][3,3][4,5][8,1][2,6]");
        
        assertEquals(94, bowlingGame.getScore());
    }
	
	public void testSpare() {
        BowlingGame bowlingGame = new BowlingGame("[1,9][3,6][7,2][3,6][4,4][5,3][3,3][4,5][8,1][2,6]");
        
        assertEquals(88, bowlingGame.getScore());
    }
	
	public void testDoubleStrike() {
        BowlingGame bowlingGame = new BowlingGame("[10,0][10,0][7,2][3,6][4,4][5,3][3,3][4,5][8,1][2,6]");
        
        assertEquals(112, bowlingGame.getScore());
    }
	
	public void testDoubleSpare() {
        BowlingGame bowlingGame = new BowlingGame("[8,2][5,5][7,2][3,6][4,4][5,3][3,3][4,5][8,1][2,6]");
        
        assertEquals(98, bowlingGame.getScore());
    }

	
	public void testStrikeSpare() {
        BowlingGame bowlingGame = new BowlingGame("[10,0][4,6][7,2][3,6][4,4][5,3][3,3][4,5][8,1][2,6]");
        assertEquals(103, bowlingGame.getScore());
    }
	
	public void testPerfectGame() {
        BowlingGame bowlingGame = new BowlingGame("[10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,0][10,10]");
        assertEquals(300, bowlingGame.getScore());
    }
	
	
	public void testString() {
		 BowlingGame bowlingGame = new BowlingGame("[10,0][4,6][7,2][3,6][4,4][5,3][3,3][4,5][8,1][2,6]");
		assertEquals(true,bowlingGame.verifyStringFormat(bowlingGame.game));
		
	}
	
	public void testStringWithNegativeNumbers() {
		 BowlingGame bowlingGame = new BowlingGame("[10,0][-4,6][7,2][3,6][-4,4][5,3][3,3][4,5][8,1][2,6]");
		assertEquals(-1,bowlingGame.getScore());
		
	}
	
	public void testLastStrikeString() {
		BowlingGame bowlingGame = new BowlingGame("[10,0][4,6][7,2][3,6][4,4][5,3][3,3][4,5][8,1][2,6][10,10]");
		assertEquals(true,bowlingGame.verifyLastStrikeStringFormat(bowlingGame.game));
		
	}
	
	public void testLastStrikeStringWithNegativeNumbers() {
		BowlingGame bowlingGame = new BowlingGame("[10,0][4,6][7,2][-3,6][4,4][5,3][3,3][-4,5][8,1][2,6][10,10]");
		assertEquals(-1,bowlingGame.getScore());
		
	}
	
	public void testLastSpareString() {
		BowlingGame bowlingGame = new BowlingGame("[10,0][4,6][7,2][3,6][4,4][5,3][3,3][4,5][8,1][2,6][10]");
		assertEquals(true,bowlingGame.verifyLastSpareStringFormat(bowlingGame.game));
		
	}
	
	public void testLastSpareStringWithNegativeNumbers() {
		BowlingGame bowlingGame = new BowlingGame("[10,0][4,6][7,2][-3,6][4,4][5,3][3,3][4,5][-8,1][2,6][10]");
		assertEquals(-1,bowlingGame.getScore());
		
	}
	
	public void testWrongFrame() {
		BowlingGame bowlingGame = new BowlingGame("[10,0][10,6][7,2][-3,6][4,4][5,3][3,3][4,5][-8,1][2,6]");
		assertEquals(-1,bowlingGame.getScore());
		
	}
	
	public void testWrongLastStrike() {
		BowlingGame bowlingGame = new BowlingGame("[10,0][10,0][7,2][-3,6][4,4][5,3][3,3][4,5][-8,1][2,6][20,20]");
		assertEquals(-1,bowlingGame.getScore());
		
	}
	
	public void testWrongLastSpare() {
		BowlingGame bowlingGame = new BowlingGame("[10,0][10,0][7,2][-3,6][4,4][5,3][3,3][4,5][-8,1][2,6][20]");
		assertEquals(-1,bowlingGame.getScore());
		
	}
}