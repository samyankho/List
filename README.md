# List

Motivation

The purpose of this assignment is to practice using file input-output, abstract classes, and interfaces through implementing 
a List data structure. In addition, the assignment will allow you to improve your understanding of how primitive data types 
are stored in Java, and what challenges might arise when storing and manipulating large collections of data.

Introduction

List 1 is an ordered collection (also known as a sequence). The user of this interface has precise control over where in the 
list each element is inserted (unlike when working with a Set). The user can access elements by their integer 
index (position in the list), and search for elements in the list. The List interface provides four methods for 
positional (indexed) access to list elements, two methods to search for a specified object, and two methods to [hopefully] 
efficiently insert and remove multiple elements at an arbitrary point in the list.

While some of these operation execute in constant amount of time, the others may execute in time proportional to the index 
value, depending on an implementation (the LinkedList class, for example, is not very efficient when accessing elements in 
the middle of the list).




Implementation

In this assignment, you will have to write a file-based implementation of the List interface, for Number-s 2 only, where the 
items are stored in a file in the local file system. The implementation should also extend an abstract FileContainer class 
provided. After that, you will have to test the performance of several operations when using your implementation.
