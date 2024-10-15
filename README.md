# Honeycomb
Meet Honeycomb, the easiest way to sequence actions for your robot. Honeycomb is lightweight with low overhead and a simple UI. 
The library is currently maintained by FTC 25943.

# How to Use Honeycomb
Honeycomb is simple and easy to use. Let's start with installation instructions.
## Installation
Navigate to the `build.dependencies.gradle` file in your code, and add the following lines.

In the repositories section, add: 
```
mavenCentral()
maven { url 'https://jitpack.io' }
```
In the dependencies section, add:
```
implementation 'com.github.Ryu-67:HoneycombV1:BETA'
```
(If you wish to use a specific version, replace BETA with whichever version tag you would like.)

## In-Code Use
Honeycomb is a 2-step process: Cell creation, and execution. Let's go over the Cell first.

### Cells
Honeycomb's fundamental unit is the Cell, a simple interface. Here's a simple example of one:

```
public class AddCell implements Cell {

    int n1, n2, n3 = 0;

    public AddCell(int n1, int n2) {

        this.n1 = n1;
        this.n2 = n2;

    }

    @Override
    public void init() {
        System.out.println("Initializing");
    }

    @Override
    public void update() {
        System.out.println("Updating");
        n3 = n1+n2;
        System.out.println(n3);
    }

    @Override
    public boolean isFinished() {
        System.out.println("Checking end condition");
        return n3 != 0;
    }

}
```
I know this is a lot, so let's break it down. To those familiar with FTCLib commands, you already know how this works. Feel free to move on.

Let's start with the class line. You can create a Cell by creating a new class and implementing the `Cell` interface.
This tells Honeycomb to assume your class will have the properties it expects a Cell to have.

Next, the constructor. This is entirely up to the end user, and serves as a place to pass in important information later on.

The override functions are the key to the Cell. Each Cell has 3 functions - `init`, `update`, and `isFinished`.
1. `init` is called first when the Cell is run. On a robot, you might do something like setting a PID target here.
2. `update` is called until the `isFinished` condition is met.
3. `isFinished` serves as a flag for the Executor to know when the Cell has completed its objective.

### Execution
The second key part of Honeycomb is Cell execution. This is done through the `Executor` class. Let's look at an example `Executor`:
```
Executor e = new Executor
            (
                    new AddCell(1,2),
                    new AddCell(1,3),
                    new CellGroup(
                            new AddCell(5,11),
                            new AddCell(12,12)
                    )
            );
```
An `Executor`'s constructor takes any amount of cells you wish to give it and runs them in a linear order.

You can see the constructor from the original `AddCell` coming into play here, where we pass in the numbers we wish to add.

You might also notice the `CellGroup`. The `CellGroup`'s constructor also takes any number of cells, or `CellGroups`, and runs them together asynchronously.

To run the Cells we have queued in the `Executor`, we do 3 things:
1. Call the `start` function. In this example, `e.start()`. This initializes the first Cell that we queued. Do this OUTSIDE of your update loop.
2. Call the `update` function IN YOUR UPDATE LOOP. In this example, `e.update()`.
3. If you wish to check the status of the executor, you can use `e.complete()`, which returns a boolean value indicating if all Cells are complete.

In a `LinearOpMode`, this may look something like:
```
...
@Override
public void runOpMode throws InterruptedException {
e.start();
while(!e.complete && opModeIsActive) {
e.update();
}
```
That's all! It really is a simple process, even though it may look like a mouthful here. Examples can be found in the `tests` package in this repository.

## Other Info
If you wish to look through a more detailed version history, you can find the jitpack page at: https://jitpack.io/#Ryu-67/HoneycombV1/BETA
