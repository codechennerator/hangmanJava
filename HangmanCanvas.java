/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		/* You fill this in */
		removeAll();
		addGScaffold();
	}
	private void addGScaffold(){
		/*Start by creating the rope
		 * NOTE:: Rope deterimines the placement of the scaffold as well.*/
		double ropeX = getWidth()/2;
		double ropeY1 = getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_BOT_OFFSET;  //subtracted 75 so I could have room for labels.
		double ropeY2 = ropeY1 + ROPE_LENGTH;
		GLine rope = new GLine(ropeX, ropeY1, ropeX, ropeY2);
		/*Then create beam.*/
		double beamX1 = ropeX - BEAM_LENGTH;
		double beamX2 = ropeX;
		double beamY = ropeY1;
		GLine beam = new GLine(beamX1, beamY, beamX2, beamY);
		/*Finally create scaffold.*/
		double scaffoldX = beamX1;
		double scaffoldY1 = beamY;
		double scaffoldY2 = scaffoldY1 + SCAFFOLD_HEIGHT;
		GLine scaffold = new GLine(scaffoldX, scaffoldY1, scaffoldX, scaffoldY2);
		/*Add all*/
		add(rope);
		add(beam);
		add(scaffold);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		/* You fill this in */
		GLabel wordDisplay = new GLabel(word,73,430);
		wordDisplay.setFont("TimesNewRoman-18");
		GObject findLabel = getElementAt(73,430);
		if (findLabel == null){
			add(wordDisplay);
		}
		else{
			remove(findLabel);
			add(wordDisplay);
		}
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		/* You fill this in */
		guessTracker++;
		incorrectGuesses(letter);
		
		/*Calculating body positions*/
		double headX = getWidth()/2 - HEAD_RADIUS; //calculates X point, its at the midpoint.
		double headY = (getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_BOT_OFFSET + ROPE_LENGTH); //calculates the Y point, based off the rope Y2.
		double bodyY1 = headY + HEAD_RADIUS*2;
		double bodyY2 = bodyY1 + BODY_LENGTH;
		double bothArmY = bodyY1 + ARM_OFFSET_FROM_HEAD;
		double leftArmX2 = getWidth()/2 - UPPER_ARM_LENGTH;
		double rightArmX2 = getWidth()/2 + UPPER_ARM_LENGTH;
		double bothHandY2 = bothArmY + LOWER_ARM_LENGTH;
		double leftHipX2 = getWidth()/2 - HIP_WIDTH;
		double rightHipX2 = getWidth()/2 + HIP_WIDTH;
		double bothLegY = bodyY2 + LEG_LENGTH;
		double leftFootX2 = leftHipX2 - FOOT_LENGTH;
		double rightFootX2 = rightHipX2 + FOOT_LENGTH;
		
		/*Where to put body position.*/
		if (guessTracker == 1){
			GOval head = new GOval(headX, headY, HEAD_RADIUS*2, HEAD_RADIUS*2);
			add(head);
		}
		else if (guessTracker == 2){
			GLine body = new GLine(getWidth()/2, bodyY1, getWidth()/2, bodyY2);
			add(body);
		}
		else if (guessTracker == 3){
			GLine leftUpper = new GLine(getWidth()/2, bothArmY, leftArmX2, bothArmY);
			GLine leftLower = new GLine(leftArmX2, bothArmY, leftArmX2, bothHandY2);
			add(leftUpper);
			add(leftLower);
		}
		else if (guessTracker == 4){
			GLine rightUpper = new GLine(getWidth()/2, bothArmY, rightArmX2, bothArmY);
			GLine rightLower = new GLine(rightArmX2, bothArmY, rightArmX2, bothHandY2);
			add(rightUpper);
			add(rightLower);
		}
		else if (guessTracker == 5) {
			GLine leftHip = new GLine(getWidth()/2, bodyY2, leftHipX2, bodyY2);
			GLine leftLeg = new GLine(leftHipX2, bodyY2, leftHipX2, bothLegY);
			add(leftHip);
			add(leftLeg);
		}
		else if (guessTracker == 6){
			GLine rightHip = new GLine(getWidth()/2, bodyY2, rightHipX2, bodyY2);
			GLine rightLeg = new GLine(rightHipX2, bodyY2, rightHipX2, bothLegY);
			add(rightHip);
			add(rightLeg);
		}
		else if (guessTracker == 7){
			GLine leftFoot = new GLine(leftHipX2, bothLegY, leftFootX2, bothLegY);
			add(leftFoot);
		}
		else if (guessTracker == 8){
			GLine rightFoot = new GLine(rightHipX2, bothLegY, rightFootX2, bothLegY);
			add(rightFoot);
		}
	}
	private void incorrectGuesses(char letter){
		concatedLetters += letter;
		GLabel letterDisplay = new GLabel (concatedLetters, 73, 450);
		letterDisplay.setFont("TimesNewRoman-12");
		GObject findLabel = getElementAt(73,450);
		if (findLabel == null){
			add(letterDisplay);
		}
		else{
			remove(findLabel);
			add(letterDisplay);
		}
	} 

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int SCAFFOLD_BOT_OFFSET = 75;
	private String concatedLetters = "";
	private int guessTracker = 0;
	

}
