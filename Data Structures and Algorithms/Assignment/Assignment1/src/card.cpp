//
//  File: card.cpp
//  --------------
//
//  Created by Connor Livsey on 18/04/13.
//  Copyright (c) 2013 Connor Livsey. All rights reserved.
//

#include "card.h"

/*
 *  Card Constructor
 *--------------------------------------------------
 *  No argument constructor
 */
Card::Card()
{

}

/*--------------------------------------------------
 *  Card Constructor
 *
 *  Argument constructor - initialises rank and suit
 */
Card::Card(Rank rank, Suit suit)
{
    this->rank = rank;
    this->suit = suit;
}

/*
 *  Card Destructor
 *--------------------------------------------------
 *
 */
Card::~Card()
{
    // NULL
}

/*
 *  GetRank
 *--------------------------------------------------
 *  Returns:    Rank of card
 */
Rank Card::GetRank()
{
    return rank;
}

/*
 *  GetSuit
 *--------------------------------------------------
 *  Returns:    Suit of card
 */
Suit Card::GetSuit()
{
    return suit;
}

/*
 *  ToString
 *--------------------------------------------------
 *  Returns:    The Suit and the Rank of the card.
 */
string Card::ToString()
{
    return RANK[rank] + SUIT[suit];
}
