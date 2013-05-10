//
//  File: deck.cpp
//  --------------
//
//  Created by Connor Livsey on 18/04/13.
//  Copyright (c) 2013 Connor Livsey. All rights reserved.
//

#include "deck.h"

/*
 *  Deck Constructor
 *--------------------------------------------------
 *  No Argument constructor
 *  Creates vector of Card* and initalises.
 */
Deck::Deck()
{
    deck = new Card*[CARDS_IN_DECK];
    for (int suit = START; suit < TOTAL_SUITS; ++suit)
    {
        for (int rank = START; rank < TOTAL_RANKS; ++rank)
        {
            //Create deck of cards
            deck[suit * TOTAL_RANKS + rank] = new Card((Rank)rank,(Suit)suit);
        }
    }
    cardsDealt = START;
}

/*
 *  Deck destructor
 *--------------------------------------------------
 *  Destructs the deck vector
 */
Deck::~Deck()
{
    for (int card = START; card < CARDS_IN_DECK; ++card)
    {
        delete deck[card];
    }
    delete deck;
}

/*
 *  DealNextCard
 *--------------------------------------------------
 *  Pre:    cardsDealt has not incremented
 *  Post:   cardsDealt incremented pointer
 *          to card returned

 *  Returns:    Null - all cards dealt
 *  Returns:    a pointer to next Card object
 */
Card* Deck::DealNextCard()
{
    if(cardsDealt > CARDS_IN_DECK)
    {
        cerr << "No cards in deck." << endl;
        return NULL;
    }
    return deck[cardsDealt++];
}

/*
 *  Shuffle
 *--------------------------------------------------
 *  Shuffles the cards in the deck by the shuffle amount
 */
void Deck::Shuffle()
{
    Random *position = new Random(START, CARDS_IN_DECK);
    for(int shuffle = START; shuffle < SHUFFLE_AMOUNT; ++shuffle)
    {
        //Randomise the shuffle by the shuffle ammount
        SwapCard(position->RandomInt(), position->RandomInt());
    }
    cardsDealt = START;
    position->~Random();
}

/*
 *  SwapCard
 *--------------------------------------------------
 *  Picks card in one position
 *  Swaps card to new position
 */
void Deck::SwapCard(int pos1, int pos2)
{
    Card *card = deck[pos1];
    deck[pos1] = deck[pos2];
    deck[pos2] = card;
    card->~Card();
}

/*
 *  DisplayDeck
 *--------------------------------------------------
 *  Displays all cards in deck.
 *  Displays in 4 rows
 */
void Deck::DisplayDeck()
{
    // Iterate through the deck, displaying in 4 rows
    for (int suit = START; suit < TOTAL_SUITS; ++suit)
    {
        for (int rank = START; rank < TOTAL_RANKS; ++rank)
        {
            cout << deck[suit * TOTAL_RANKS + rank]->ToString() << "\t";
        }
        cout << endl;
    }
}
