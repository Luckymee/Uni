//
//  File: cardComparer.cpp
//  ----------------------
//
//  Created by Connor Livsey on 19/04/13.
//  Copyright (c) 2013 Connor Livsey. All rights reserved.
//

#include "cardComparer.h"

/*
 *  CardComparer
 *--------------------------------------------------
 *  Compares two cards
 *  Returns: True if card one less than card two
 */
bool CardComparer::operator()(Card *card1, Card *card2)
{
    if(card1->GetRank() < card2->GetRank())
    {
        return true;
    }
    return false;
}
