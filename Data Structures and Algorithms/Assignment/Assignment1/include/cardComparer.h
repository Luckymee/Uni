//
//  File: cardComparer.h
//  --------------------
//  This interface provides a function for determining the
//  order of cards in a hand.
//
//  Created by Connor Livsey on 19/04/13.
//  Copyright (c) 2013 Connor Livsey. All rights reserved.
//

#ifndef CARD_COMPARER
#define CARD_COMPARER

#include <iostream>
#include "card.h"

class CardComparer
{
public:
    /*
     *  CardComparer
     *--------------------------------------------------
     *  Compares two cards
     *  Returns: True if card one less than card two
     */
    bool operator() (Card*, Card*);
};
#endif /* defined(CARD_COMPARER) */
