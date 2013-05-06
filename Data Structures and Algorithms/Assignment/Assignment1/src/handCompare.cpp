//
//  File: handComparer.cpp
//  ----------------------
//
//  Created by Connor Livsey on 22/04/13.
//  Copyright (c) 2013 Connor Livsey. All rights reserved.
//

#include "handCompare.h"

/*
 *  HandComparer
 *--------------------------------------------------
 *  Compares two hands
 *  Returns: True if hand one less than hand two
 */
bool HandComparer::operator() (Hand *handOne, Hand *handTwo)
{
    if (handOne->GetHandType() == handTwo->GetHandType())
    {
        return (handOne->GetValue() < handTwo->GetValue());
    }
    else
    {
        return handOne->GetHandType() < handTwo->GetHandType();
    }
}
