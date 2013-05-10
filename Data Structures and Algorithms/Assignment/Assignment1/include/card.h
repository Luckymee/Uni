//
//  File: card.h
//  ------------
//  Interface defining functionality for a playing card type.  A playing card
//  has a suit and a rank.
//
//  Created by Connor Livsey on 18/04/13.
//  Copyright (c) 2013 Connor Livsey. All rights reserved.
//

#ifndef CARD_H
#define CARD_H

#include <iostream>
#include <string>


using namespace std;


const int TOTAL_SUITS = 4;
const int TOTAL_RANKS = 13;

enum Rank
{
    TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
};

enum Suit
{
    DIAMONDS, CLUBS, HEARTS, SPADES
};
const string SUIT[TOTAL_SUITS] = {"H", "D", "C", "S"};
const string RANK[TOTAL_RANKS] = {"2", "3", "4", "5", "6", "7", "8",
                                  "9", "T", "J", "Q", "K", "A"
                                 };

class Card
{

private:
    Rank rank;
    Suit suit;

public:
    /*
     *  Card Constructor
     *--------------------------------------------------
     *  No argument constructor
     */
    Card();

    /*--------------------------------------------------
     *  Card Constructor
     *
     *  Argument constructor - initialises rank and suit
     */
    Card(Rank rank, Suit suit);

    /*
     *  Card Destructor
     *--------------------------------------------------
     *
     */
    ~Card();

    /*
     *  GetRank
     *--------------------------------------------------
     *  Returns:    Rank of card
     */
    Rank GetRank();

    /*
     *  GetSuit
     *--------------------------------------------------
     *  Returns:    Suit of card
     */
    Suit GetSuit();

    /*
     *  ToString
     *--------------------------------------------------
     *  Returns:    The Suit and the Rank of the card.
     */
    string ToString();
};

#endif /* defined(CARD_H) */
