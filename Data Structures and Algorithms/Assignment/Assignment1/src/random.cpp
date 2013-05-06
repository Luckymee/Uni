//
//  File: random.cpp
//  ----------------
//
//  Created by Connor Livsey on 14/04/13.
//  Copyright (c) 2013 Connor Livsey. All rights reserved.
//

#include "random.h"

/*
 *  Random Constructor
 *--------------------------------------------------
 *  Initialises random generator
 */
Random::Random(int low, int high)
{
    this->low = low;
    this->high = high;
    Randomise();
}

/*
 *  Random Destructor
 *--------------------------------------------------
 *  Deconstruct Random
 */
Random::~Random()
{
    //NULL
}

/*
 *  RandomInt
 *--------------------------------------------------
 *  Pre:    Random number not generated
 *  Post:   Random number generated from high and low
 *
 *  Returns:    Randomised int
 */
int Random::RandomInt()
{
    double random = double(rand() / (double(RAND_MAX) + 1));
    int randomInt = (int)(random * (high - low));
    return low + randomInt;
}

/*
 *  Randomise
 *--------------------------------------------------
 *  Implements the random-number generator.
 */
void Random::Randomise()
{
    srand(int(time(NULL)));
}
