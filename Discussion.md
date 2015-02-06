Recitation Discussion
=====================

by Sid Gopinath (sdg23) and Robert Vann (rcv4)


We chose a small chunk of code to refactor. Our team had two methods to change the speed of the simulation. One sped up the simulation and one slowed it down.
I was unable to find a good way to refactor that because there was a check in the slow down method to make sure the speed (which was an int) didn't become 0.
We refactored the two methods into one "changeSimulationSpeed" method that took in a boolean of true or false. If it was true, it would speed up the simulation. If false, it would slow it down. If it was a slow down, the check would also be implemented.

While this did eliminate several lines and an entire method, I am uncomfortable with the if statements that we used. They even ended up being nested, which doesn't look great. I think it is justified in this case, but I am not certain.

