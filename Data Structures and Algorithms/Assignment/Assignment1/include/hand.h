//
//  File: hand.h
//  ------------
//  This interface provides several functions for dealing
//  5 cards to each player and evaluating each hand based
//  on type and rank.
//
//  Created by Connor Livsey on 19/04/13.
//  Copyright (c) 2013 Connor Livsey. All rights reserved.
//

#ifndef HAND_H
#define HAND_H

#include <iostream>
#include <vector>
#include <list>
#include <sstream>
#include "card.h"
#include "cardComparer.h"
#include <math.h>
#include <algorithm>

enum HandType
{
    HIGH_CARD, ONE_PAIR, TWO_PAIRS, THREE_OF_A_KIND,
    STRAIGHT, FLUSH, FULL_HOUSE, FOUR_OF_A_KIND,
    STRAIGHT_FLUSH
};

const string HAND_TYPE[9] =
{
    "High Card", "One Pair", "Two Pairs", "Three of a Kind",
    "Straight", "Flush", "Full House", "Four of a Kind",
    "Straight Flush"
};

class Hand
{
public:
    /*
    *  Hand - Constructor
    *--------------------------------------------------
    *  Sets player identifier
    */
    Hand(int player);

    /*
     *	~Hand - Distructor
     *--------------------------------------------------
     *	Deletes hand vector
     */
    ~Hand();

    /*
     *	AddCard
     *--------------------------------------------------
     *	Adds card to hand, check if full before adding
     */
    void AddCard(Card *card);

    /*
     * Evaluate
     *--------------------------------------------------
     *  Pre:     Unevaluated hands
     *  Post:    Evaluated hands
     *--------------------------------------------------
     */
    void Evaluate();

    /*
     *	GetValue
     *--------------------------------------------------
     *  Returns: Value of hand
     */
    int GetValue();

    /*
     *	GetHandType
     *--------------------------------------------------
     *  Returns: Hand type
     */
    HandType GetHandType();

    /*
    * ClearHand
    *--------------------------------------------------
    *  Pre:	    Hand vector contains cards
    *  Post:	All cards removed from hand vector
    *--------------------------------------------------
    */
    void ClearHand();

    /*
     *	ToString
     *--------------------------------------------------
     *  Returns: All the cards in hand
     */
    string ToString();
private:
    const int CARDS_IN_HAND = 5;
    const int FIRST = 0;
    const int FIRST_CARD = 1;
    const int ONE_CARD = 1;
    const int ONE_RANK = 1;
    std::stringstream out;
    vector<Card*> hand;
    int Id;
    int value;
    HandType type;
    enum Kind
    {
        FOUR = 4,
        THREE = 3,
        PAIR = 2
    };

    /*
     * EvaluateType
     *--------------------------------------------------
     *  Pre:	    Unknown hand
     *  Post:    Evaluated hand type
     *
     *  Evaluates the hand on each hand type available
     *
     */
    void EvaluateType();

    /*
     * EvaluateValue
     *--------------------------------------------------
     *  Pre:	    Predetermined hand type
     *  Post:    Hand value assigned
     *
     *  Evaluates the value of each hand type
     *
     */
    void EvaluateValue();

    /*
     * EvaluateStandard
     *--------------------------------------------------
     *   Pre:	Hand without value
     *   Post:	Hand with value
     *
     *	Evaluates the value of the hand
     *
     */
    void EvalStandard();

    /*
     * EvaluateStraight
     *--------------------------------------------------
     *   Pre:	Straight hand without value
     *   Post:	Straight hand with value
     *
     *	Evaluates the value of a Straight hand
     *
     */
    void EvalStraight();

    /*
     * IsFlush
     *--------------------------------------------------
     *	Check if all the cards have same suit
     *
     *	Returns: true if the hand is flush
     */
    bool IsFlush();

    /*
     * IsStraight
     *--------------------------------------------------
     *   Checks if hand is straight
     *   Low to High: Continuous Set
     *
     *	Returns: true if hand is straight
     */
    bool IsStraight();

    /*
     * IsN_Of_A_Kind
     *--------------------------------------------------
     *	Checks if hand is Three or Four of a Kind
     *
     *	Returns: true if a kind is found
     */
    bool IsN_Of_A_Kind(Kind kind, bool sort);

    /*
     * IsPair
     *--------------------------------------------------
     *	Check if hand is pair
     *	Check if 2 pairs of pairs
     *	Returns: true if a pair found
     *	Returns: true if 2 pairs found;
     */
    bool IsPair(Kind kind, bool two, bool sort);
};

#endif /* defined(HAND_H) */
