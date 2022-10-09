// READ THE HEADER FILE FOR MORE DOCUMENTATION
#include "tl04.h"


/**
 * \brief Pointers to the ends of the list
 *
 * These pointers keep track of where the singly-linked list is in memory. The
 * [queue_head] pointer points to the first node of the list, and [queue_tail]
 * likewise points to the last.
 *
 * Initially, both of these pointers are `NULL`. The list is initially empty, so
 * there is no first or last node.
 *
 * \property extern queue_node_t *queue_head
 * \property extern queue_node_t *queue_tail
 */
struct queue_node_t *queue_head = NULL;
struct queue_node_t *queue_tail = NULL;


/**
 * \brief Add students to the queue
 *
 * This function will be called by client code to add a student to the end of
 * the queue. The caller will supply the data of the student to add.
 *
 * This function should wrap the [data] in a [queue_node_t] on the heap, and
 * deep-copy all the other data. In particular, the name of the student should
 * be moved onto the heap as well.
 *
 * This function should return `SUCCESS` if the student was added successfully.
 * If it fails, it should return `FAILURE` and leave the list unchanged. It
 * should fail if and only if:
 * * `malloc` fails,
 * * the student's name is `NULL`, or
 * * the student's name is an empty string.
 *
 * \param[in] data Data of the student to enqueue
 * \return Whether the student was successfully added
 */
int queue_add(struct student_t data) {
    if (data.name == NULL || strlen(data.name) == 0) {
        return FAILURE;
    }
    struct queue_node_t* newNode;
    if (!(newNode = malloc(sizeof(struct queue_node_t)))) {
        return FAILURE;
    }
    char* newName;
    if (!(newName = malloc(strlen(data.name) + 1))) {
        free(newNode);
        return FAILURE;
    }
    strcpy(newName, data.name);
    newNode -> data.name = newName;
    newNode -> data.assignment = data.assignment;
    newNode -> next = NULL;

    if (queue_head == NULL) {
        queue_head = newNode;
        queue_tail = newNode;
    } else {
        queue_tail -> next = newNode;
        queue_tail = newNode;
    }
    return SUCCESS;
}

/**
 * \brief Remove students from the queue
 *
 * This function will be called by client code to remove a student from the
 * front the queue. It will return whether a student was removed successfully,
 * and the data removed in that case.
 *
 * The way this function returns the data is somewhat strange. To get around the
 * limitation that functions may only have one return value, the caller will
 * pass in a pointer where the student's data should be stored. This function
 * will store the returned data at that pointer. Independently, it will return
 * whether it succeeded via the normal path.
 *
 * If this function succeeds, it should return `SUCCESS` and modify `*data` to
 * be the data of the student removed. If it fails, it should return `FAILURE`
 * and leave both the list and `*data` unchanged. It should fail if and only if:
 * * [data] is `NULL`, or
 * * the list is empty.
 *
 * \param[out] data Where to put the data of the removed student
 * \return Whether a student was successfully removed
 */
int queue_remove(struct student_t *data) {
    if (data == NULL) {
        return FAILURE;
    }
    if (queue_head == NULL) {
        return FAILURE;
    } else if (queue_head == queue_tail) {
        *data = queue_head -> data;
        strcpy(data -> name, queue_head -> data.name);
        free(queue_head -> data.name);
        free(queue_head);
        queue_head = NULL;
        queue_tail = NULL;
        return SUCCESS;

    } else {
        *data = queue_head -> data;
        strcpy(data -> name, queue_head -> data.name);
        struct queue_node_t* next = queue_head -> next;
        free(queue_head -> data.name);
        free(queue_head);
        queue_head = next;
        return SUCCESS;

    }
}
