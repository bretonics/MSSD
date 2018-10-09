(function() {

    // Functions
    function buildQuiz() {
        const output = [];

        // Loop through each question
        questions.forEach((currentQuestion, questionNumber) => {
                const answers = [];

                // Add radio button to each question's answer
                for(letter in currentQuestion.answers) {
                    answers.push(
                        `<label>
                            <input type="radio" name="question${questionNumber}" value="${letter}">
                            ${letter} :
                            ${currentQuestion.answers[letter]}
                        </label><br>`
                    );
                }

                // Add question and its answers to output
                output.push(
                    `<div class="question"> ${currentQuestion.question} </div>
                    <div class="answers"> ${answers.join('')} </div><br>`
                );
            }
        );

        // Put output to HTML page
        quizContainer.innerHTML = output.join("");
    }

    function showResults() {

        // Get answer containers from quiz
        const answerContainers = quizContainer.querySelectorAll(".answers");
        let scores = { corgi: 0, bernese: 0, german: 0, stzu: 0, american: 0, golden: 0, terrier: 0, labrador: 0, beagle: 0, havanese: 0, spaniel: 0, bulldog: 0, husky: 0, french: 0, collie: 0, mixed: 0 };

        questions.forEach((currentQuestion, questionNumber) => {
            const userAnswer = (answerContainers[questionNumber].querySelector(`input[name=question${questionNumber}]:checked`) || {}).value;

            if (questionNumber === 0) {
                if (userAnswer === 'A') {
                    scores.corgi += 3;
                    scores.american += 5;
                    scores.golden += 8;
                    scores.labrador += 8;
                    scores.beagle += 5;
                    scores.bulldog += 5;
                }
                else if (userAnswer === 'B') {
                    scores.german += 8;
                    scores.bernese += 5;
                    scores.husky += 5;
                }
                else if (userAnswer === 'C') {
                    scores.corgi += 8;
                    scores.beagle += 5;
                    scores.golden += 8;
                    scores.american += 5;
                    scores.bulldog += 5;
                    scores.french += 5;
                    scores.stzu += 3;
                }
                else {
                    scores.husky += 100;
                    scores.collie += 100;
                    scores.german += 100;
                    scores.spaniel += 100;
                    scores.bernese += 100;
                }
            }

            else if (questionNumber === 1) {
                if (userAnswer==='A') {
                    scores.stzu += 80;
                    scores.havanese += 80;
                }
                else if (userAnswer === 'B') {
                    scores.terrier += 80;
                    scores.beagle += 80;
                    scores.french += 80;
                    scores.corgi += 80;
                }
                else if (userAnswer === 'C') {
                    scores.american += 80;
                    scores.golden += 80;
                    scores.spaniel += 80;
                    scores.bulldog += 80;
                    scores.husky += 80;
                    scores.collie += 80;
                }
                else {
                    scores.bernese += 80;
                    scores.labrador += 80;
                    scores.german += 80;
                }
            }

            else if (questionNumber === 2) {
                if (userAnswer === 'A') {
                    scores.corgi += 8;
                    scores.havanese += 8;
                    scores.stzu += 8;
                    scores.terrier += 5;
                    scores.french += 8;
                }
                else if (userAnswer === 'B') {
                    scores.collie += 8;
                    scores.spaniel += 5;
                    scores.german += 5;
                    scores.american += 5;
                }
                else if (userAnswer === 'C') {
                    scores.labrador += 8;
                    scores.golden += 13;
                    scores.beagle += 5;
                }
                else {
                    scores.bernese += 8;
                    scores.bulldog += 3;
                    scores.husky += 5;
                }
            }

            else if (questionNumber === 3) {
                if (userAnswer === 'A') {
                    scores.mixed += 0;
                }
                else {
                    scores.mixed += 1000;
                }
            }

            else {
                if (userAnswer === 'A') {
                    scores.stzu += 13;
                    scores.havanese += 13;
                }
                else if (userAnswer === 'B') {
                    scores.corgi += 5;
                    scores.havanese += 5;
                    scores.stzu += 5;
                    scores.beagle += 5;
                    scores.french += 5;
                    scores.terrier += 5;
                }
                else if (userAnswer === 'C') {
                    scores.german += 3;
                    scores.american += 3;
                    scores.golden += 3;
                    scores.labrador += 3;
                    scores.spaniel += 3;
                    scores.bernese += 3;
                }
                else {
                    scores.husky += 5;
                    scores.collie += 8;
                    scores.spaniel += 5;
                    scores.german += 8;
                }
            }
        }
        );

                var one = 0;
        var two = 0;
        var three = 0;
        var breed1 = ""
        var breed2 = ""
        var breed3 = ""


        for (var key in scores)
        {
            if (scores[key] > one) {
            	one = scores[key];
        		breed1 = key;
            }

            else if (scores[key] > two)
            {
            	two = scores[key];
            	breed2 = key;
            }

        	else if (scores[key] > three)
        	{
        		three = scores[key];
        		breed3 = key
        	}

        	else
        	{
        		continue;
        	}
        }

        console.log("The breeds which are best for you are: "+breed1+", "+breed2+", "+breed3+", ")

        switch (breed1)
        {
            case "corgi" : window.location.replace("https://www.akc.org/dog-breeds/pembroke-welsh-corgi/"); break;
            case "bernese" : window.location.replace("https://www.akc.org/dog-breeds/bernese-mountain-dog/"); break;
            case "german" : window.location.replace("https://www.akc.org/dog-breeds/german-shepherd-dog/"); break;
            case "stzu" : window.location.replace("https://www.akc.org/dog-breeds/shih-tzu/"); break;
            case "american" : window.location.replace("https://www.akc.org/dog-breeds/miniature-american-shepherd/"); break;
            case "golden" : window.location.replace("https://www.akc.org/dog-breeds/golden-retriever/"); break;
            case "terrier" : window.location.replace("https://www.akc.org/dog-breeds/boston-terrier/"); break;
            case "labrador" : window.location.replace("https://www.akc.org/dog-breeds/labrador-retriever/"); break;
            case "beagle" : window.location.replace("https://www.akc.org/dog-breeds/beagle/"); break;
            case "havanese" : window.location.replace("https://www.akc.org/dog-breeds/havanese/"); break;
            case "spaniel" : window.location.replace("https://www.akc.org/dog-breeds/english-springer-spaniel/"); break;
            case "bulldog" : window.location.replace("https://www.akc.org/dog-breeds/bulldog/"); break;
            case "husky" : window.location.replace("https://www.akc.org/dog-breeds/siberian-husky/"); break;
            case "french" : window.location.replace("https://www.akc.org/dog-breeds/french-bulldog/"); break;
            case "collie" : window.location.replace("https://www.akc.org/dog-breeds/border-collie/"); break;
            case "mixed" : window.location.replace("https://www.boredpanda.com/mixed-breed-dogs/"); break;
            default : window.location.replace("https://www.akc.org/register/information/canine-partners/"); break;
        }
    }

    // Element references
    const quizContainer = document.getElementById("quiz");
    const resultsContainer = document.getElementById("results");
    const submitButton = document.getElementById("submit");

    // Question bank
    const questions = [

        // Question 1
        {
            question: "Why do you want a dog?",
            answers: {
                A: "For a friend to hang out with at home.",
                B: "To protect my home.",
                C: "To pick up girls / guys at the dog park.",
                D: "To do work at a farm",
            },
        },

        // Question 2
        {
            question: "How large do you want your dog to be?",
            answers: {
                A: "I want a very small dog.",
                B: "I want a small dog.",
                C: "I want a medium size dog.",
                D: "I want a big dog that weighs more than 50 lbs / 22 kilos.",
            },
        },

        // Question 3
        {
            question: "Pick an adjective that you want to describe your dog?",
            answers: {
                A: "Cute",
                B: "Smart",
                C: "Friendly",
                D: "Strong",
            },
        },

        // Question 4
        {
            question: "Would you like your dog to be a purebred?",
            answers: {
                A: "Yes, I want a purebred dog.",
                B: "No, mixed breed dogs are fine with me!",
            },
        },

        // Question 5
        {
            question: "How large is your living space?",
            answers: {
                A: "I live in an apartment, with no easy access to outdoor space.",
                B: "I live in an apartment, with with easily accessible outdoor space.",
                C: "I live in a house/apartment, with easy access to outdoor space.",
                D: "I live on a farm or estate with a very large amount of open space.",
            },
        },

    ];

    // Display quiz
    buildQuiz();

    // Show results on submit
    submitButton.addEventListener('click', showResults);
    }
)();
