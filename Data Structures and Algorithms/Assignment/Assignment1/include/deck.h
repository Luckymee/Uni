//
//  File: deck.h
//  ------------
//  This interface provides several functions for generating
//  a deck of 52 cards.
//
//  Created by Connor Livsey on 18/04/13.
//  Copyright (c) 2013 Connor Livsey. All rights reserved.
//

#ifndef DECK_H
#define DECK_H

#include <iostream>
#include "card.h"
#include "random.h"

const int CARDS_IN_DECK = 52;

class Deck
{

public:
    /*
     *  Deck Constructor
     *--------------------------------------------------
     *  No Argument constructor
     *  Creates vector of Card* and initalises.
     */
    Deck();

    /*
     *  Deck destructor
     *--------------------------------------------------
     *  Destructs the deck vector
     */
    ~Deck();

    /*
     *  DealNextCard
     *--------------------------------------------------
     *  Pre:     cardsDealt has not incremented
     *  Post:    cardsDealt incremented pointer
     *           to card returned

     *  Returns:    Null - all cards dealt
     *  Returns:    a pointer to next Card object
     */
    Card* DealNextCard();

   /*
    *  Shuffle
    *--------------------------------------------------
    *  Shuffles the cards in the deck by the shuffle amount
    */
    void Shuffle();

    /*
     *  DisplayDeck
     *--------------------------------------------------
     *  Displays all cards in deck.
     *  Displays in 4 rows
     */
    void DisplayDeck();

private:
    const int SHUFFLE_AMOUNT = 1000;
    const int START = 0;
    int cardsDealt;
    Card** deck;

    /*
     *  SwapCard
     *--------------------------------------------------
     *  Picks card in one position
     *  Swaps card to new position
     */
    void SwapCard(int pickPos, int placePos);
};

#endif /* defined(DECK_H) */
