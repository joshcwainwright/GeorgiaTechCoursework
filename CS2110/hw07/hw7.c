/**
 * @file hw7.c
 * @author YOUR NAME HERE
 * @collaborators NAMES OF PEOPLE THAT YOU COLLABORATED WITH HERE
 * @brief structs, pointers, pointer arithmetic, arrays, strings, and macros
 * @date 2022-03-xx
 */

// DO NOT MODIFY THE INCLUDE(S) LIST
#include <stdio.h>
#include "hw7.h"
#include "my_string.h"

// Global array of Animal structs
struct animal animals[MAX_ANIMAL_LENGTH];

int size = 0;

/** addAnimal
 *
 * @brief creates a new Animal and adds it to the array of Animal structs, "animals"
 *
 *
 * @param "species" species of the animal being created and added
 *               NOTE: if the length of the species (including the null terminating character)
 *               is above MAX_SPECIES_LENGTH, truncate species to MAX_SPECIES_LENGTH. If the length
 *               is 0, return FAILURE.
 *
 * @param "id" id of the animal being created and added
 * @param "hungerScale" hunger scale of the animal being created and added
 * @param "habitat" habitat of the animal being created and added
 * @return FAILURE on failure, SUCCESS on success
 *         Failure if any of the following are true:
 *         (1) "species" length is 0
 *         (2) "habitat" length is 0
 *         (3) adding the new animal would cause the size of the array "animals" to
 *             exceed MAX_ANIMAL_LENGTH
 *
 */
int addAnimal(const char *species, int id, double hungerScale, const char *habitat)
{
    if (my_strlen(species) == 0 || my_strlen(habitat) == 0 || size == 64) {
        return FAILURE;
    }

    struct animal a;
    if (my_strlen(species) + 1 > MAX_SPECIES_LENGTH) {
        *(a.species + 9) = '\0';
        my_strncpy(a.species, species, MAX_SPECIES_LENGTH - 1);
    } else {
        my_strncpy(a.species, species, my_strlen(species) + 1);
    }
    if (my_strlen(habitat) + 1 > MAX_HABITAT_LENGTH) {
        *(a.habitat + 9) = '\0';
        my_strncpy(a.habitat, habitat, MAX_HABITAT_LENGTH - 1);
    } else {
        my_strncpy(a.habitat, habitat, my_strlen(habitat) + 1);
    }
    a.id = id;
    a.hungerScale = hungerScale;
    animals[size] = a;
    size++;
    return SUCCESS;
}

/** updateAnimalSpecies
 *
 * @brief updates the species of an existing animal in the array of Animal structs, "animals"
 *
 * @param "animal" Animal struct that exists in the array "animals"
 * @param "species" new species of Animal "animal"
 *               NOTE: if the length of species (including the null terminating character)
 *               is above MAX_SPECIES_LENGTH, truncate species to MAX_SPECIES_LENGTH
 * @return FAILURE on failure, SUCCESS on success
 *         Failure if any of the following are true:
 *         (1) the Animal struct "animal" can not be found in the array "animals" based on its id
 */
int updateAnimalSpecies(struct animal animal, const char *species)
{
    int i = 0;
    while (i < size) {
        if (animals[i].id == animal.id) {
            if (my_strlen(species) + 1 > MAX_SPECIES_LENGTH) {
                *(animals[i].species + 9) = '\0';
                my_strncpy(animals[i].species, species, MAX_SPECIES_LENGTH - 1);
            } else {
                my_strncpy(animals[i].species, species, my_strlen(species) + 1);
            }
            return SUCCESS;
        }
        i++;
    }
    return FAILURE;
}

/** averageHungerScale
* @brief Search for all animals with the same species and find average the hungerScales
*
* @param "species" Species that you want to find the average hungerScale for
* @return the average hungerScale of the specified species
*         if the species does not exist, return 0.0
*/
double averageHungerScale(const char *species)
    {
    double sum = 0.0;
    double count = 0;
    int i = 0;
    while (i < size) {
        if(my_strncmp(species, animals[i].species, my_strlen(species)) == 0) {
            sum += animals[i].hungerScale;
            count++;
        }
        i++;
    }
    if (sum != 0.0) {
        return sum / count;
    } else {
        return sum;
    }
}

/** swapAnimals
 *
 * @brief swaps the position of two Animal structs in the array of Animal structs, "animals"
 *
 * @param "index1" index of the first Animal struct in the array "animals"
 * @param "index2" index of the second Animal struct in the array "animals"
 * @return FAILURE on failure, SUCCESS on success
 *         Failure if any of the following are true:
 *         (1) "index1" and/or "index2" are negative numbers
 *         (2) "index1" and/or "index2" are out of bounds of the array "animals"
 */
int swapAnimals(int index1, int index2)
{
    if (index1 >= size || index2 >= size || index1 < 0 || index2 < 0) {
        return FAILURE;
    }
    struct animal temp;
    temp = animals[index1];
    animals[index1] = animals[index2];
    animals[index2] = temp;
    return SUCCESS;
}

/** compareHabitat
 *
 * @brief compares the two Animals animals' habitats (using ASCII)
 *
 * @param "animal1" Animal struct that exists in the array "animals"
 * @param "animal2" Animal struct that exists in the array "animals"
 * @return negative number if animal1 is less than animal2, positive number if animal1 is greater
 *         than animal2, and 0 if animal1 is equal to animal2
 */
int compareHabitat(struct animal animal1, struct animal animal2)
{
return my_strncmp(animal1.habitat, animal2.habitat, MAX_HABITAT_LENGTH);
}

/** removeAnimal
 *
 * @brief removes Animal in the array of Animal structs, "animals", that has the same species
 *
 * @param "animal" Animal struct that exists in the array "animals"
 * @return FAILURE on failure, SUCCESS on success
 *         Failure if any of the following are true:
 *         (1) the Animal struct "animal" can not be found in the array "animals"
 */
int removeAnimal(struct animal animal)
{
    int i = 0;
    while(i < size){
        if (animals[i].id == animal.id) {
            while (i < size - 1) {
                animals[i] = animals[i + 1];
                i++;
            }
            size--;
            return SUCCESS;
        }
        i++;
    }
    return FAILURE;
}


/** sortAnimal
 *
 * @brief using the compareHabitat function, sort the Animals in the array of
 * Animal structs, "animals," by the animals' habitat
 * If two animals have the same habitat, place the hungier animal first
 *
 * @param void
 * @return void
 */
void sortAnimalsByHabitat(void)
{
    int i = 0;
    while (i < size) {
        int j = i + 1;
        while (j < size) {
            if (compareHabitat(animals[j], animals[i]) < 0) {
                swapAnimals(j, i);
            } else if (compareHabitat(animals[j], animals[i]) == 0) {
                if (animals[j].hungerScale > animals[i].hungerScale) {
                    swapAnimals(j, i);
                }
            }
            j++;
        }
        i++;
    }
}
