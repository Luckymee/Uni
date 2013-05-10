//
//  File: random.h
//  --------------
//  This interface provides several functions for generating
//  pseudo-random numbers.
//
//  Created by Connor Livsey on 18/04/13.
//  Copyright (c) 2013 Connor Livsey. All rights reserved.
//

#ifndef RANDOM_H
#define RANDOM_H
#include <iostream>
#include <cstdlib>
#include <ctime>

class Random
{

private:
    int low;
    int high;

    /*
    *  Randomise
    *--------------------------------------------------
    *  Implements the random-number generator.
    */
    void Randomise();

public:
    /*
     *  Random Constructor
     *--------------------------------------------------
     *  Initialises random generator
     */
    Random(int low, int high);

    /*
     *  Random Destructor
     *--------------------------------------------------
     *  Deconstruct Random
     */
    ~Random();

    /*
     *  RandomInt
     *--------------------------------------------------
     *  Pre:    Random number not generated
     *  Post:   Random number generated from high and low
     *
     *  Returns:    Randomised int
     */
    int RandomInt();
};

#endif /* defined(RANDOM_H) */
