//
//  File: handComparer.h
//  --------------------
//  This interface provides a function for determining the
//  order of hands in a poker game.
//
//  Created by Connor Livsey on 22/04/13.
//  Copyright (c) 2013 Connor Livsey. All rights reserved.
//

#ifndef HAND_COMPARER
#define HAND_COMPARER
#include <iostream>
#include "hand.h"

class HandComparer
{
public:
    /*
     *  HandComparer
     *--------------------------------------------------
     *  Compares two hands
     *  Returns: True if hand one less than hand two
     */
    bool operator() (Hand*, Hand*);
};
#endif /* defined(HAND_COMPARER) */
