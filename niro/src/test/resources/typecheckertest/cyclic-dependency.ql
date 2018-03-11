form CircularDependency {
	"Question1" q1: boolean = (q2)
	"Question2" q2: boolean = (q3)
	"Question3" q3: boolean = (q4)
	"Question4" q4: boolean = (q1)
}